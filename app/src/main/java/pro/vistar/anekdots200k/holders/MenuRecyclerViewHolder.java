package pro.vistar.anekdots200k.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import pro.vistar.anekdots200k.R;

public class MenuRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView menuItemCount;
    public TextView menuItemName;
    public ImageView menuItemPicture;

    public MenuRecyclerViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        menuItemName = (TextView)itemView.findViewById(R.id.menu_item_name);
        menuItemCount = (TextView)itemView.findViewById(R.id.menu_item_count);
        menuItemPicture = (ImageView)itemView.findViewById(R.id.menu_item_picture);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(), "Clicked Country Position = " + getPosition(), Toast.LENGTH_SHORT).show();
    }
}