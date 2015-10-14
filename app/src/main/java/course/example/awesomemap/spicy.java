package course.example.awesomemap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * Created by sadaf on 29-Sep-15.
 */
public class spicy extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spicy);
        Intent intent = getIntent();
        String message = intent.getStringExtra("name");

        ImageView imageView = (ImageView) findViewById(R.id.pic);
        int imageResource =
                getResources().getIdentifier(message + "_large", "drawable", getPackageName());
        imageView.setImageResource(imageResource);


    }

}
