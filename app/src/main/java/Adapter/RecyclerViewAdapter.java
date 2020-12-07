package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.hitorhealgameapp.R;
import java.util.List;
import Player.Player;


public class RecyclerViewAdapter extends  RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private List<Player> playerList;
    public  Context context;

    public RecyclerViewAdapter(List<Player> playerList, Context context) {
        this.playerList = playerList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.player_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {

        Player player = playerList.get(position);

        holder.playerName.setText(player.getName());
        holder.playerExperience.setText("Experience\n" + player.getExperience());

    }

    @Override
    public int getItemCount() {
        if (playerList != null & playerList.size() > 10) {
            return 10;
        }else if(playerList.size() <= 10){
            return playerList.size();
        }else{
            return 0;
        }
    }

 class ViewHolder extends RecyclerView.ViewHolder {

    TextView playerName, playerExperience;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        playerName = itemView.findViewById(R.id.playerName);
        playerExperience = itemView.findViewById(R.id.playerExperience);


    }
 }
}

