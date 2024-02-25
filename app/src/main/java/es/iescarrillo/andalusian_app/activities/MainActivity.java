package es.iescarrillo.andalusian_app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;

import es.iescarrillo.andalusian_app.R;

public class MainActivity extends AppCompatActivity {

    private ImageButton btnPlay, btnStop;

    private AssetManager assetManager;

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnPlay=findViewById(R.id.btnPlay);
        btnStop=findViewById(R.id.btnStop);

        //Cargar la lista de canciones del directorio assets
        assetManager= getAssets();


        //Accion del boton reproducir
        btnPlay.setOnClickListener(v -> {

            try {
                if (mediaPlayer == null) {
                    initializeMediaPlayer();
                }

                mediaPlayer.start();


            } catch (Exception e) {
                Log.e("Error", e.getMessage());
            }

        });

        
        //Accion del boton parar
        btnStop.setOnClickListener(v -> {
            if(mediaPlayer!=null){
                mediaPlayer.stop();
                mediaPlayer.reset();
                mediaPlayer.release();
                mediaPlayer=null;


            }
        });





    }

    /**
     * Metodo para inicializar el mediaPlayer
     */
    private void initializeMediaPlayer() {
        mediaPlayer = new MediaPlayer();
        try {
            AssetFileDescriptor descriptor = assetManager.openFd("himno.mp3");
            mediaPlayer.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
            mediaPlayer.prepare();
        } catch (Exception e) {
            Log.e("Error", "Error al inicializar el MediaPlayer", e);
        }
    }


}