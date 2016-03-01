package com.meetme.android.multistateview.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.meetme.android.multistateview.MultiStateView;

public class MainActivity extends AppCompatActivity {
    int mState;

    private MultiStateView mMultiStateView;

    private TextView mExampleOfHowToGetContentView;

    private TextView mStateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStateView = (TextView) findViewById(R.id.state);
        mMultiStateView = (MultiStateView) findViewById(R.id.content);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        mState = mMultiStateView.getContentState();
        mExampleOfHowToGetContentView = (TextView) mMultiStateView.getContentView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setStateText(mState);
    }

    private void setStateText(int state) {
        mStateView.setText(String.format("State: %s", state));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_rotate_state) {
            // This is only done because we're rotating state; you'd typically just call direct to mMultiStateView#setState(ContentState)
            switch (mState) {
                case MultiStateView.CONTENT_STATE_ID_CONTENT:
                    setState(MultiStateView.CONTENT_STATE_ID_LOADING);
                    break;
                case MultiStateView.CONTENT_STATE_ID_LOADING:
                    setState(MultiStateView.CONTENT_STATE_ID_ERROR_NETWORK);
                    break;
                case MultiStateView.CONTENT_STATE_ID_ERROR_NETWORK:
                    setState(MultiStateView.CONTENT_STATE_ID_ERROR_GENERAL);
                    break;
                case MultiStateView.CONTENT_STATE_ID_ERROR_GENERAL:
                    setState(MultiStateView.CONTENT_STATE_ID_CONTENT);
                    break;
            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setState(int state) {
        setStateText(state);
        mMultiStateView.setContentState(state);
        mState = state;
    }
}
