package strategicbriefs.com;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {

    private Context mContext;
    private List<Teacher> teachers;
    private OnItemClickListener mListener;

    public RecyclerAdapter (Context context, List<Teacher> uploads) {
        mContext = context;
        teachers = uploads;

    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.activity_row_model, parent, false);
        return new RecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        Teacher currentTeacher = teachers.get(position);
        holder.nameTextView.setText(currentTeacher.getName());
        holder.descriptionTextView.setText(currentTeacher.getDescription());
        holder.keyFriendsTextView.setText(currentTeacher.getKeyFirends());
        holder.dateTextView.setText(getDateToday());
        Picasso.get()
                .load(currentTeacher.getImageURI())
                .placeholder(R.drawable.image_place_holder)
                .resize(50, 50)
                .centerCrop()
                .into(holder.suspectImageView);


    }

    @Override
    public int getItemCount() {
        return teachers.size();
    }

    public enum RecyvlerViewHolder {}


    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {

        private TextView nameTextView, descriptionTextView, keyFriendsTextView, dateTextView;
        public ImageView suspectImageView;

        public RecyclerViewHolder (View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.nameTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            keyFriendsTextView = itemView.findViewById(R.id.keyFrindsTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            suspectImageView = itemView.findViewById(R.id.suspectImageView);



            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onClick (View v) {
            if (mListener != null) {
              int postion = getAdapterPosition();
              if (postion !=RecyclerView.NO_POSITION) {
                  mListener.OnItemClick(postion);
              }
            }
        }

        @Override
        public void onCreateContextMenu (ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Select Action");
            MenuItem showItem = menu.add(Menu.NONE, 1, 1, "Show");
            MenuItem deleteItem = menu.add(Menu.NONE, 2, 2, "Delete");

            showItem.setOnMenuItemClickListener(this);
            deleteItem.setOnMenuItemClickListener(this);
        }


        @Override
        public boolean onMenuItemClick (MenuItem item) {
            if (mListener !=null) {
                int postion = getAdapterPosition();
                if (postion !=RecyclerView.NO_POSITION) {
                   switch (item.getItemId()) {

                        case 1:
                            mListener.onShowItemClick(postion);
                            return true;

                       case 2:
                           mListener.onDeleteItemClick(postion);
                           return true;




                    }

                }

            }
            return false;

        }

    }

    public interface OnItemClickListener {
        void OnItemClick(int position);
        void onShowItemClick(int position);
        void onDeleteItemClick(int position);

    }


    public void setOnItemClickListener (OnItemClickListener listener) {
        mListener = listener;


    }
    private String getDateToday (){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd");
        Date date = new Date();
        String today = dateFormat.format(date);
        return today;
    }

}
