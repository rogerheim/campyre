package campyre.android;

import android.content.Context;
import android.os.AsyncTask;
import campyre.java.Campfire;
import campyre.java.CampfireException;
import campyre.java.Room;

public class FindRoomTask extends AsyncTask<String, Void, Room> {
    private FindsRoom context;
    private CampfireException exception = null;
    private Object tag = null;

    public FindRoomTask(FindsRoom context, Object tag) {
        this.context = context;
        this.tag = tag;
    }

    public void onScreenLoad(FindsRoom context) {
        this.context = context;
    }

    @Override
    protected Room doInBackground(String... roomid) {
        try {
            return Room.find(context.getCampfire(), roomid[0]);
        } catch (CampfireException e) {
            this.exception = e;
            return null;
        }
    }

    public CampfireException getException() {
        return this.exception;
    }

    @Override
    protected void onPostExecute(Room room) {
        context.onFoundRoom(room, tag);
    }

    public interface FindsRoom {
        public void onFoundRoom(Room room, Object tag);
        public Context getContext();
        public Campfire getCampfire();
    }
}
