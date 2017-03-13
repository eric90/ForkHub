package com.github.mobile.ui.commit;

import android.view.View;
import android.widget.AdapterView;

import org.eclipse.egit.github.core.Commit;

/**
 * Created by rkwagner on 3/13/17.
 */

public class CommitType extends CommitTypes {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id, Object item) {
        CommitDiffListFragment f = new CommitDiffListFragment();
        f.startActivity(CommitViewActivity.createIntent(f.repository,
                ((Commit) item).getSha()));
    }
}
