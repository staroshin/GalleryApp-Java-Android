package edu.ewubd.galleryapp;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class AdapterClass extends BaseAdapter {

    Context context;
    ArrayList<ImageList> arrayList;

    String imageURL, description;

    public AdapterClass(Context context, ArrayList<ImageList> arrayList){
        this.context = context;
        this.arrayList = arrayList;

    }
    @Override
    public int getCount() {
        return this.arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.image_view, parent, false);

        ImageView img_view = rowView.findViewById(R.id.img);



        ImageList ImageList = arrayList.get(position);


        imageURL = ImageList.getimageID();
        description = ImageList.getDescription();
        Picasso.get().load("https://muthosoft.com/univ/photos/"+ ImageList.getimageID()).resize(1000,1000).into(img_view);



        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ImageDesc.class);
                intent.putExtra("imageID", ImageList.getimageID());
                intent.putExtra("imageDes", ImageList.getDescription());
                context.startActivity(intent);
            }
        });

        return rowView;
    }
}