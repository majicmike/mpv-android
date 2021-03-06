package is.xyz.mpv;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nononsenseapps.filepicker.FilePickerFragment;
import com.nononsenseapps.filepicker.LogicHandler;

import java.io.File;

public class MPVFilePickerFragment extends FilePickerFragment {

    @Override
    protected void setupToolbar(Toolbar toolbar) {
        toolbar.setVisibility(View.GONE);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {}

    @Override
    public void onClickCheckable(View v, CheckableViewHolder vh) {
        if (!allowMultiple) {
            // Clear is necessary, in case user clicked some checkbox directly
            mCheckedItems.clear();
            mCheckedItems.add(vh.file);
            onClickOk(null);
        } else {
            super.onClickCheckable(v, vh);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType != LogicHandler.VIEWTYPE_CHECKABLE)
            return super.onCreateViewHolder(parent, viewType);
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.filepicker_item, parent, false);
        return new CheckableViewHolder(v);
    }

    public boolean isBackTop() {
        return compareFiles(mCurrentPath, new File("/")) == 0;
    }
}
