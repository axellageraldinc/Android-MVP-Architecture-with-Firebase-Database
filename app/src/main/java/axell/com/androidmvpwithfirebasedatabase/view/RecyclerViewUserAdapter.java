package axell.com.androidmvpwithfirebasedatabase.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import axell.com.androidmvpwithfirebasedatabase.R;
import axell.com.androidmvpwithfirebasedatabase.model.User;

public class RecyclerViewUserAdapter extends RecyclerView.Adapter<RecyclerViewUserAdapter.ViewHolder> {
    private List<User> userList;
    private Context context;

    public RecyclerViewUserAdapter(List<User> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_user_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = userList.get(position);
        holder.txtUserId.setText(user.getId());
        holder.txtUserName.setText(user.getName());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtUserId, txtUserName;
        public ViewHolder(final View itemView) {
            super(itemView);
            txtUserId = itemView.findViewById(R.id.txtUserId);
            txtUserName = itemView.findViewById(R.id.txtUserName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showToastOfClickedItem(userList.get(getLayoutPosition()));
                }
            });
        }
        private void showToastOfClickedItem(User user){
            Toast.makeText(context, "Clicked item : " + user.getName(), Toast.LENGTH_SHORT).show();
        }
    }
}
