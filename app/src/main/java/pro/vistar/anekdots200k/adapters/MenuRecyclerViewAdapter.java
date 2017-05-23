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

public class MenuRecyclerViewAdapter extends RecyclerView.Adapter<MenuRecyclerViewHolder> {

    private List<ThemeEntity> itemList;
    private Context context;
    private int nameFontSize = 16;
    private int countFontSize = 10;

    public MenuRecyclerViewAdapter(Context context, List<ThemeEntity> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    /**
     * Set font size for chapter name
     * @param nameFontSize - font size for chapter name
     */
    public void setNameFontSize(int nameFontSize) {
        this.nameFontSize = nameFontSize;
    }

    /**
     * Set font size for count in chapter
     * @param countFontSize - font size for count
     */
    public void setCountFontSize(int countFontSize) {
        this.countFontSize = countFontSize;
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
        holder.menuItemName.setTextSize(this.nameFontSize);

        holder.menuItemCount.setText(String.valueOf(itemList.get(position).getCnt()));
        holder.menuItemCount.setTextSize(this.countFontSize);

        //holder.menuItemPicture.setImageResource(itemList.get(position).);
        holder.menuItemPicture.setImageResource(R.mipmap.ic_launcher_round);
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}