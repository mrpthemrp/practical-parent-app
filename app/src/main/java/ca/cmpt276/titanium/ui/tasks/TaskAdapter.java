package ca.cmpt276.titanium.ui.tasks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import ca.cmpt276.titanium.R;
import ca.cmpt276.titanium.model.ChildManager;
import ca.cmpt276.titanium.model.Task;

/**
 * This is an adapter for the task list.
 */
public class TaskAdapter extends ArrayAdapter<Task> {
  public TaskAdapter(Context context, ArrayList<Task> tasks) {
    super(context, 0, tasks);
  }

  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    if (convertView == null) {
      convertView =
          LayoutInflater.from(getContext()).inflate(R.layout.item_task, parent, false);
    }

    ChildManager childManager = ChildManager.getInstance(getContext());

    Task task = getItem(position);
    String taskName = task.getTaskName();
    String childName =
        task.getChildUniqueID() == null
            ? getContext().getString(R.string.default_name_no_children)
            : childManager.getChild(task.getChildUniqueID()).getName();

    TextView taskNameText = convertView.findViewById(R.id.TextView_item_task_task_name);
    taskNameText.setText(getContext().getString(R.string.tasks_task_name, taskName));

    TextView childNameText = convertView.findViewById(R.id.TextView_item_task_child_name);
    childNameText.setText(getContext().getString(R.string.tasks_next_child_name, childName));

    return convertView;
  }
}