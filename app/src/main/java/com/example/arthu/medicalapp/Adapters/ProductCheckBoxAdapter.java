package com.example.arthu.medicalapp.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arthu.medicalapp.Entity.Product;
import com.example.arthu.medicalapp.Entity.ProductQuantity;
import com.example.arthu.medicalapp.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductCheckBoxAdapter extends ArrayAdapter<ProductQuantity> {

    List<ProductQuantity> modelItems = null;
    Context context;
    Map<ProductQuantity, Boolean> productMap;

    public ProductCheckBoxAdapter(Context context, List<ProductQuantity> resource) {
        super(context, R.layout.activity_row, resource);
        this.context = context;
        this.modelItems = resource;
        productMap = new HashMap<ProductQuantity, Boolean>(resource.size());
    }

    private class ViewHolder {
        TextView qtd;
        CheckBox checkBox;
    }

    public int getCount() {
        return modelItems.size();
    }

    public ProductQuantity getItem(int position) {
        return modelItems.get(position);
    }

    public Map<Product, Boolean> getItemChecked(){
        Map<Product, Boolean> map = new HashMap<>();

        for (Map.Entry<ProductQuantity, Boolean> item : productMap.entrySet()){
            if(item.getValue()){
                map.put(item.getKey(), item.getValue());
            }
        }

        return map;
    }

    public void checkProduct(String code){
        for (Map.Entry<ProductQuantity,Boolean> item : productMap.entrySet()) {
            if(item.getKey().getCode() == code){
                item.setValue(true);
            }
        }
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_row, parent, false);
            holder = new ViewHolder();
            holder.qtd = (TextView) convertView.findViewById(R.id.qtdProductField);
            holder.checkBox = (CheckBox) convertView.findViewById(R.id.cbProduct);
            ProductQuantity productQuantity = modelItems.get(position);
            holder.checkBox.setText(productQuantity.getName());

            int quantity = productQuantity.getQuantity();

            if(quantity > 0){
                holder.qtd.setText(Integer.toString(quantity));
                holder.checkBox.setChecked(true);
            }

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                productMap.put(modelItems.get(position), holder.checkBox.isChecked());
            }
        });


        for (ProductQuantity prod: modelItems) {
            productMap.put(prod, false);
        }

        return convertView;
    }
}
