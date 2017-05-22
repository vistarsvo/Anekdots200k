package pro.vistar.anekdots200k.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import pro.vistar.anekdots200k.R;
import pro.vistar.anekdots200k.data.sqlite.entity.ThemeEntity;
import pro.vistar.anekdots200k.holders.MenuRecyclerViewHolder;

public class MenuRecyclerViewAdapter  extends RecyclerView.Adapter<MenuRecyclerViewHolder> {

    private List<ThemeEntity> itemList;
    private Context context;

    public MenuRecyclerViewAdapter(Context context, List<ThemeEntity> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public MenuRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_card_view_list_item, null);
        MenuRecyclerViewHolder rcv = new MenuRecyclerViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(MenuRecyclerViewHolder holder, int position) {
        holder.menuItemName.setText(itemList.get(position).getShortname());
        holder.menuItemCount.setText(String.valueOf(itemList.get(position).getCnt()));
        //holder.menuItemPicture.setImageResource(itemList.get(position).);
        holder.menuItemPicture.setImageResource(R.mipmap.ic_launcher_round);
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}