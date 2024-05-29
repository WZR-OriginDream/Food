package com.wzr.food.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.wzr.food.R;
import com.wzr.food.logic.model.CreateArticleBean;

import java.util.LinkedList;

public class CreateIngredientAdapter extends RecyclerView.Adapter<CreateIngredientAdapter.ViewHolder> {

    private LinkedList<CreateArticleBean.Ingredients> ingredients;
    private Context context;

    public CreateIngredientAdapter(Context context, LinkedList<CreateArticleBean.Ingredients> ingredients) {
        this.ingredients = ingredients;
        this.context = context;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        EditText etName, etAmount;
        ImageView ivDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            etName = itemView.findViewById(R.id.et_name);
            etAmount = itemView.findViewById(R.id.et_amount);
            ivDelete = itemView.findViewById(R.id.iv_delete);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.create_ingredient_item, parent, false);
        ViewHolder holder = new ViewHolder(view);

        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if (ingredients != null && ingredients.size() > 0) {
                    ingredients.remove(position);
                    notifyDataSetChanged();
                }
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.setIsRecyclable(false);
        holder.etName.setTag(position);
        holder.etAmount.setTag(position);

        holder.etName.setText(ingredients.get(position).getName());
        holder.etAmount.setText(ingredients.get(position).getAmount());

        holder.etName.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (holder.etName.getTag() == (Integer) position) {//设置tag解决错乱问题
                    onNameFillListener.onNameFill(position, s.toString());
                }
            }
        });

        holder.etAmount.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (holder.etAmount.getTag() == (Integer) position) {
                    onAmountFillListener.onAmountFill(position, s.toString());
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    private OnNameFillListener onNameFillListener;
    private OnAmountFillListener onAmountFillListener;

    public interface OnNameFillListener {
        void onNameFill(int position, String name);
    }

    public interface OnAmountFillListener {
        void onAmountFill(int position, String amount);
    }

    public void setOnNameFillListener(OnNameFillListener onNameFillListener) {
        this.onNameFillListener = onNameFillListener;
    }

    public void setOnAmountFillListener(OnAmountFillListener onAmountFillListener) {
        this.onAmountFillListener = onAmountFillListener;
    }
}