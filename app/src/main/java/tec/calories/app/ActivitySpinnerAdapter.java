package tec.calories.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import tec.calories.app.models.Activity_level;

public class ActivitySpinnerAdapter extends ArrayAdapter<Activity_level> {

    public ActivitySpinnerAdapter(Context context, List<Activity_level> activityLevels) {
        super(context, R.layout.activity_spinner, activityLevels);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_spinner, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textView = convertView.findViewById(R.id.nameTextView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Activity_level activityLevel = getItem(position);
        if (activityLevel != null) {
            viewHolder.textView.setText(activityLevel.getName());
        }

        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    static class ViewHolder {
        TextView textView;
    }

    public Activity_level getSelectedActivityLevel(int position) {
        return getItem(position);
    }
}
