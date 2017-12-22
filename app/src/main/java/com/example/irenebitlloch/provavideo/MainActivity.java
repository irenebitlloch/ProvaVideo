package com.example.irenebitlloch.provavideo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import org.jcodec.common.model.Rational;

import org.jcodec.api.FrameGrab;
import org.jcodec.api.SequenceEncoder;
import org.jcodec.api.android.AndroidSequenceEncoder;
import org.jcodec.common.AndroidUtil;
import org.jcodec.common.RunLength;
import org.jcodec.common.io.NIOUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.jcodec.common.io.SeekableByteChannel;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
            Encoder();

    }

    private void Encoder() {
        SeekableByteChannel out = null;
        try {
            out = NIOUtils.writableFileChannel("/tmp/output.mp4");

            AndroidSequenceEncoder encoder = new AndroidSequenceEncoder(out, Rational.R(25, 1));
            for (int i =0; i < Fotos.length; i++) {
                // Generate the image, for Android use Bitmap
                Bitmap image = BitmapFactory.decodeResource(getResources(), Fotos[i]);
                // Encode the image
                encoder.encodeImage(image);
            }
            // Finalize the encoding, i.e. clear the buffers, write the header, etc.
            encoder.finish();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            NIOUtils.closeQuietly(out);
        }
    }
    private class Encoder extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... surls) {
            String cont = WebFetcher.getUrl(surls[0]);
            return cont;
        }


        private Integer[] Fotos = {
      R.drawable.baixa, R.drawable.baixa1,
            R.drawable.baixa, R.drawable.baixa1,
            R.drawable.baixa, R.drawable.baixa1,
            R.drawable.baixa, R.drawable.baixa1,
            R.drawable.baixa, R.drawable.baixa1,
            R.drawable.baixa, R.drawable.baixa1,
    };



}
