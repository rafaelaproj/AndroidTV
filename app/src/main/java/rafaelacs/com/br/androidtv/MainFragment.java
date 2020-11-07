package rafaelacs.com.br.androidtv;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.leanback.app.BrowseSupportFragment;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.BaseCardView;
import androidx.leanback.widget.HeaderItem;
import androidx.leanback.widget.ImageCardView;
import androidx.leanback.widget.ListRow;
import androidx.leanback.widget.ListRowPresenter;
import androidx.leanback.widget.OnItemViewClickedListener;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.Row;
import androidx.leanback.widget.RowPresenter;

public class MainFragment extends BrowseSupportFragment implements OnItemViewClickedListener {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setUI();
    }

    private void setUI() {
        //In-App title will be shown in top-right corner
        setTitle("AndroidTV");
        //To show or hide the category list the content is divided
        setHeadersState(HEADERS_ENABLED);
        //Set the background color of BrowseLane
        setBrandColor(Color.LTGRAY);

        loadRows();

        setOnItemViewClickedListener(this);
    }

    private void loadRows() {
        HeaderItem moviesCategory = new HeaderItem(0, "Filmes");
        HeaderItem sportsCategory = new HeaderItem(1, "Esportes");
        ArrayObjectAdapter adapterForRow = new ArrayObjectAdapter(new MyPresenter());

        adapterForRow.add(new SingleRowView("Captain Marvel",
                getContext().getDrawable(R.drawable.captain_marvel)));
        adapterForRow.add(new SingleRowView("Lady and the Tramp",
                getContext().getDrawable(R.drawable.lady_and_tramp)));
        adapterForRow.add(new SingleRowView("Star Wars IX - The Rise of Skywalker",
                getContext().getDrawable(R.drawable.sw_rise_of_skywalker)));

        ArrayObjectAdapter windowAdapter = new ArrayObjectAdapter((new ListRowPresenter()));
        windowAdapter.add(new ListRow(moviesCategory, adapterForRow));
        //windowAdapter.add(new ListRow(sportsCategory, adapterForRow));
        setAdapter(windowAdapter);
    }

    @Override
    public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
        Intent intent = new Intent(getActivity(), DetailsActivity.class);
        startActivity(intent);
    }


    private class MyPresenter extends Presenter {
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent) {
            ImageCardView imageCardView = new ImageCardView(parent.getContext());
            imageCardView.setCardType(BaseCardView.CARD_TYPE_INFO_UNDER_WITH_EXTRA);
            imageCardView.setInfoVisibility(BaseCardView.CARD_REGION_VISIBLE_ACTIVATED);

            return new ViewHolder(imageCardView);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, Object item) {
            SingleRowView singleRowView = (SingleRowView) item;
            ImageCardView imageCardView = (ImageCardView) viewHolder.view;
            imageCardView.setMainImage(singleRowView.getImage());
            imageCardView.setMainImageDimensions(313, 176);
            imageCardView.setTitleText(singleRowView.getName());
            imageCardView.setContentText("Sinopse...");
        }

        @Override
        public void onUnbindViewHolder(ViewHolder viewHolder) {
            //Nothing here
        }
    }
}
