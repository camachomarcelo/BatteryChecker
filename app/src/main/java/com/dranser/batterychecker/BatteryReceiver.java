package com.dranser.batterychecker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.BatteryManager;
import android.widget.ImageView;
import android.widget.TextView;

public class BatteryReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        TextView estadoLabel = ((MainActivity)context).findViewById(R.id.estadoLabel);
        TextView porcentajeLabel = ((MainActivity)context).findViewById(R.id.porcentajeLabel);
        ImageView bateriaImagen = ((MainActivity)context).findViewById(R.id.bateriaImagen);

        String accion = intent.getAction();

        if (accion != null && accion.equals(Intent.ACTION_BATTERY_CHANGED)){

            // Estado

            int estado = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
            String mensaje = "";

            switch (estado){
                case BatteryManager.BATTERY_STATUS_FULL:
                    mensaje = "Completo";
                    break;
                case BatteryManager.BATTERY_STATUS_CHARGING:
                    mensaje = "Cargando";
                    break;
                case BatteryManager.BATTERY_STATUS_DISCHARGING:
                    mensaje = "Descargando";
                    break;
                case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                    mensaje = "No cargando";
                    break;
                case BatteryManager.BATTERY_STATUS_UNKNOWN:
                    mensaje = "Desconocido";
                    break;
            }
            estadoLabel.setText(mensaje);

            // Porcentaje

            int nivel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int escala = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            int porcentaje = nivel * 100 / escala;
            porcentajeLabel.setText(porcentaje + "%");

            // Imagen

            Resources res = context.getResources();

            if (porcentaje >= 90){
                bateriaImagen.setImageDrawable(res.getDrawable(R.drawable.b100));
            } else if (90 > porcentaje && porcentaje >= 65 ){
                bateriaImagen.setImageDrawable(res.getDrawable(R.drawable.b75));
            } else if (65 > porcentaje && porcentaje >= 40){
                bateriaImagen.setImageDrawable(res.getDrawable(R.drawable.b50));
            } else if (40 > porcentaje && porcentaje >= 15){
                bateriaImagen.setImageDrawable(res.getDrawable(R.drawable.b25));
            } else {
                bateriaImagen.setImageDrawable(res.getDrawable(R.drawable.b0));
            }

        }

    }
}





















