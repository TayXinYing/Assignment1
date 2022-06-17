package sg.edu.np.mad.Assignment1;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.text.format.DateFormat;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.ui.PlayerView;

import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;

public class AdapterVideo extends RecyclerView.Adapter<AdapterVideo.HolderVideo> {

    private Context context;

    private ArrayList<ModelVideos> videosArrayList;

    TextView videoTitle, videoTime;



    public AdapterVideo(Context context, ArrayList<ModelVideos> videosArrayList) {
        this.context = context;
        this.videosArrayList = videosArrayList;
    }

    @NonNull
    @Override
    public HolderVideo onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.listedvideos, parent, false);

        return new HolderVideo(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderVideo holder, int position) {

        ExoPlayer player = new ExoPlayer.Builder(context).build();

        holder.videoView.setPlayer(player);

        ModelVideos modelVideos = videosArrayList.get(position);

        String id = modelVideos.getId();
        String title = modelVideos.getTitle();
        String timestamp = modelVideos.getTimestamp();
        String videoUrl  = modelVideos.getVideoUrl();

        //format timestamp
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(timestamp));
        String formattedDateTime = DateFormat.format("dd/MM/yyyy hh:mm", calendar).toString();

        //set data
        holder.videoTitle.setText(title);
        holder.videoTime.setText(formattedDateTime);

        MediaItem mediaItem = MediaItem.fromUri(videoUrl);
        player.setMediaItem(mediaItem);
        player.prepare();
        player.setPlayWhenReady(false);
    }


    @Override
    public int getItemCount() {
        return videosArrayList.size(); //return size of list
    }

    class HolderVideo extends RecyclerView.ViewHolder{

        PlayerView videoView;
        TextView videoTitle, videoTime;

        public HolderVideo(@NonNull View itemView) {
            super(itemView);

            videoView = itemView.findViewById(R.id.videoViews);
            videoTitle = itemView.findViewById(R.id.videoTitle);
            videoTime = itemView.findViewById(R.id.videoTime);


        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {

        return position;
    }
}
