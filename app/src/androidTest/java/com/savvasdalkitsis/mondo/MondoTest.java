package com.savvasdalkitsis.mondo;

import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;

import com.savvasdalkitsis.mondo.test.ui.actors.User;
import com.savvasdalkitsis.mondo.view.transactions.TransactionsActivity;

import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MondoTest extends ActivityInstrumentationTestCase2<TransactionsActivity> {

    public MondoTest() {
        super(TransactionsActivity.class);
    }

    protected User user;

    public void setUp() throws Exception {
        super.setUp();
        user = new User(this);
    }

    public void startApp() {
        getActivity();
    }
}