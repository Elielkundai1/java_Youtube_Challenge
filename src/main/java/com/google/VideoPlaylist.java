package com.google;

import java.util.HashMap;
import java.util.Map;
/** A class used to represent a Playlist */
class VideoPlaylist {

    public String playlistName;

    Map<String, String> map = new HashMap<String, String>();
map.add("Play video", "Amazing cats");
map.add("Pause video", "Amazing cats");
map.put("Stop video", "Amazing cats");
System.out.println(map.get("Amazing cats"));


    public VideoPlaylist(String playlistName)
    {
        this.playlistName = playlistName;
    }
    public String getPlaylistName()
    {
        return playlistName;
    }

    public String createPlaylist()
    {
        return toString();
    }
}
