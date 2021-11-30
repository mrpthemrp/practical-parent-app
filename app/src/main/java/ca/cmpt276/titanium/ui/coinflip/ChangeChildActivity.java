package ca.cmpt276.titanium.ui.coinflip;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

import ca.cmpt276.titanium.R;
import ca.cmpt276.titanium.model.Child;
import ca.cmpt276.titanium.model.ChildManager;
import ca.cmpt276.titanium.ui.ChildAdapter;

/**
 * This activity represents the children list.
 * Allows the user to change the current child turn.
 */
public class ChangeChildActivity extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_coin_flip_change_child);

    setSupportActionBar(findViewById(R.id.ToolBar_change_child));
    Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

    displayChildren();
  }

  private void displayChildren() {
    ChildManager childManager = ChildManager.getInstance(this);

    TextView emptyStateMessage = findViewById(R.id.TextView_change_child_empty_state);
    emptyStateMessage.setVisibility(
        childManager.getChildren().size() == 0
            ? View.VISIBLE
            : View.INVISIBLE);

    ArrayList<UUID> coinFlipQueue = new ArrayList<>(childManager.getCoinFlipQueue());
    ArrayList<Child> coinFlipQueueChildren = new ArrayList<>();
    coinFlipQueue.forEach(uniqueID -> coinFlipQueueChildren.add(childManager.getChild(uniqueID)));
    ChildAdapter childAdapter = new ChildAdapter(this, coinFlipQueueChildren);

    ListView changeChildListView = findViewById(R.id.ListView_change_child);
    changeChildListView.setAdapter(childAdapter);
    changeChildListView.setOnItemClickListener((parent, view, position, id) -> {
      childManager.moveToFrontOfQueue(coinFlipQueue.get(position));
      finish();
    });
  }
}
