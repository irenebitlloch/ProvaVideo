package com.example.irenebitlloch.provavideo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Rational;

import org.jcodec.api.FrameGrab;
import org.jcodec.api.SequenceEncoder;
import org.jcodec.common.RunLength;
import org.jcodec.common.io.NIOUtils;

import java.io.File;
import java.nio.channels.SeekableByteChannel;



public class MainActivity extends AppCompatActivity {








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        SeekableByteChannel out = null;
        try {
            out = NIOUtils.writableFileChannel("/tmp/output.mp4");
            // for Android use: AndroidSequenceEncoder
            AWTSequenceEncoder encoder = new AWTSequenceEncoder(out, Rational.R(25, 1));
            for (int i =0; i < Fotos.length; i++) {


                // Generate the image, for Android use Bitmap
                BufferedImage image = Fotos[i];
                // Encode the image
                encoder.encodeImage(image);
            }
            // Finalize the encoding, i.e. clear the buffers, write the header, etc.
            encoder.finish();
        }
        finally {
            NIOUtils.closeQuietly(out);
        }





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
