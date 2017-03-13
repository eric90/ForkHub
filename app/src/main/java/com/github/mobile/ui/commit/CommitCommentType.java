package com.github.mobile.ui.commit;

import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;

import org.eclipse.egit.github.core.CommitComment;
import org.eclipse.egit.github.core.CommitFile;

/**
 * Created by rkwagner on 3/13/17.
 */

public class CommitCommentType extends CommitTypes{
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id, Object item) {
        CommitDiffListFragment f = new CommitDiffListFragment();
        if (!TextUtils.isEmpty(((CommitComment) item).getPath()))
            f.selectPreviousFile(position, item, parent);
    }
}
