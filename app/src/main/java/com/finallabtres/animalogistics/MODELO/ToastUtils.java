package com.finallabtres.animalogistics.MODELO;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.finallabtres.animalogistics.R;
import com.google.android.material.imageview.ShapeableImageView;

public class ToastUtils {
    public static void showToast(Context context, String message, int color,int img) {
        // Inflar el layout personalizado para el Toast
        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.toastview, null);

        layout.setBackgroundColor( color);

        // Configurar el texto en el TextView del Toast
        TextView text = layout.findViewById(R.id.toast_text);
        text.setText(message);

        //icono
        ImageView imagen = layout.findViewById(R.id.toast_image);
        imagen.setImageResource(img);

        //background
        ShapeableImageView toastBg = layout.findViewById(R.id.toastBg);
        toastBg.setImageResource(color);


        // Crear y mostrar el Toast personalizado
        Toast toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
}
