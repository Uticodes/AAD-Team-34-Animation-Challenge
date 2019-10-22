package com.example.tekquotez;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private final LayoutInflater mInflater;
    private final Context mContext;

    RecyclerViewAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.quote_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //display quote in the textView
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            final ImageView image_like = itemView.findViewById(R.id.imageView_like);
            final TextView quoteText = itemView.findViewById(R.id.textView_quote);
            image_like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    image_like.setImageResource(R.drawable.ic_favorite_black_24dp);
                    image_like.setColorFilter(Color.RED);

                    AnimatorSet animatorSet = new AnimatorSet();

                    ObjectAnimator animatorScaleX = ObjectAnimator.ofFloat(image_like, "scaleX", 1.0f, 1.5f);
                    ObjectAnimator animatorScaleY = ObjectAnimator.ofFloat(image_like, "scaleY", 1.0f, 1.5f);

                    animatorScaleX.setDuration(1000);
                    animatorScaleX.setRepeatMode(ValueAnimator.REVERSE);
                    animatorScaleX.setRepeatCount(1);

                    animatorScaleY.setDuration(1000);
                    animatorScaleY.setRepeatMode(ValueAnimator.REVERSE);
                    animatorScaleY.setRepeatCount(1);


                    animatorSet.play(animatorScaleX).with(animatorScaleY);
                    animatorSet.start();
                }
            });
            quoteText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //start the detailActivity
                    Intent intent = new Intent(mContext, DetailActivity.class);
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
