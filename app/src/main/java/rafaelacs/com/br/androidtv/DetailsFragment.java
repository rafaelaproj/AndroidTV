package rafaelacs.com.br.androidtv;

import android.os.Bundle;

import androidx.leanback.app.DetailsSupportFragment;
import androidx.leanback.widget.Action;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.ClassPresenterSelector;
import androidx.leanback.widget.DetailsOverviewRow;
import androidx.leanback.widget.FullWidthDetailsOverviewRowPresenter;
import androidx.leanback.widget.SparseArrayObjectAdapter;

import static android.media.session.PlaybackState.ACTION_PLAY;
import static android.media.session.PlaybackState.ACTION_REWIND;

public class DetailsFragment extends DetailsSupportFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        buildUI();
    }

    private void buildUI() {

        ClassPresenterSelector selector = new ClassPresenterSelector();
        selector.addClassPresenter(DetailsOverviewRow.class,
                new FullWidthDetailsOverviewRowPresenter(
                        new DetailsDescriptionPresenter()
                )
        );

        ArrayObjectAdapter adapter = new ArrayObjectAdapter(selector);
        DetailsOverviewRow detailsOverview = new DetailsOverviewRow("Detalhes do Item de Media");
        //Add images and action button to the details view
        detailsOverview.setImageDrawable(getActivity()
                                        .getResources()
                .getDrawable(R.drawable.captain_marvel));

        //Add some actions
        SparseArrayObjectAdapter actionAdapter = new SparseArrayObjectAdapter();
        actionAdapter.set((int) ACTION_PLAY, new Action(1, "Comprar R$ 9,99"));
        actionAdapter.set((int) ACTION_REWIND, new Action(2, "Alugar R$ 4,99"));
        detailsOverview.setActionsAdapter(actionAdapter);
        adapter.add(detailsOverview);
        setAdapter(adapter);
    }
}
