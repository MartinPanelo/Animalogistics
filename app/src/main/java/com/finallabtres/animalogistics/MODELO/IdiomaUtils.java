package com.finallabtres.animalogistics.MODELO;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableRow;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.os.LocaleListCompat;

import com.finallabtres.animalogistics.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Locale;

public class IdiomaUtils {

    // Método para mostrar el BottomSheetDialog para cambiar el idioma
    public static void mostrarOpcionesIdioma(final Context context) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
        View view1 = LayoutInflater.from(context).inflate(R.layout.opcionessheet, null);
        bottomSheetDialog.setContentView(view1);
        bottomSheetDialog.show();

        TableRow MBEspañol = view1.findViewById(R.id.TREspaniol);
        TableRow MBEnglish = view1.findViewById(R.id.TREnglish);

        MBEspañol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   saveLocale(context, "es");
                FijarIdioma("es");
            }
        });

        MBEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  saveLocale(context, "en");
                FijarIdioma("en");
            }
        });
    }

    // Método para guardar el idioma en SharedPreferences
/*    public static void saveLocale(Context context, String languageCode) {
        SharedPreferences preferences = context.getSharedPreferences("SP", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("idioma", languageCode);
        editor.apply();
    }*/

    // Método para fijar el idioma
    public static void FijarIdioma(String idioma) {
        AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(idioma));
    }

    // Método para obtener el idioma guardado en SharedPreferences
/*    public static String getSavedLocale(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("SP", Context.MODE_PRIVATE);
        return preferences.getString("idioma", Locale.getDefault().getLanguage());
    }*/
}
