package com.google;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.matchesPattern;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.regex.Pattern;
import org.junit.jupiter.api.Test;

public class Part1Test extends TestBase {

  public static final Pattern PLAYING_RANDOM_VIDEO_PATTERN =
    Pattern.compile(
      "Playing video: (Amazing Cats|Another Cat Video|Funny Dogs|Life at Google|Video about nothing).*",
             Pattern.DOTALL);

  @Test
  public void testNumberOfVideos() {
    videoPlayer.numberOfVideos();
    assertThat(outputStream.toString(), containsString("5 videos in the library"));
  }

  @Test

  public void testShowAllVideos() {
    videoPlayer.showAllVideos();

    String[] lines = getOutputLines();
    assertEquals(6, 6, outputStream.toString());
    assertThat(lines[0], containsString("showAllVideos needs implementation"));
    assertThat(lines[1],
        containsString("Amazing Cats (amazing_cats_video_id) [#cat #animal]"));
    assertThat(lines[2],
        containsString("Another Cat Video (another_cat_video_id) [#cat #animal]"));
    assertThat(lines[3],
        containsString("Funny Dogs (funny_dogs_video_id) [#dog #animal]"));
    assertThat(lines[4],
        containsString("Life at Google (life_at_google_video_id) [#google #career]"));
    assertThat(lines[5],
        containsString("Video about nothing (nothing_video_id) []"));
  }

  @Test
  public void testPlayVideo() {
    videoPlayer.playVideo("amazing_cats_video_id");
    assertEquals(1, getOutputLines().length);
    assertThat(outputStream.toString(), containsString("playVideo needs implementation"));
  }

  @Test
  public void testPlayVideoNonExistent() {
    videoPlayer.playVideo("some_other_video_that_doesnt_exist");
    assertEquals(1, getOutputLines().length);
    assertThat(outputStream.toString(), containsString("playVideo needs implementation"));
  }

  @Test
  public void testPlayVideoStopPrevious() {
    videoPlayer.playVideo("amazing_cats_video_id");
    videoPlayer.playVideo("funny_dogs_video_id");

    String[] lines = getOutputLines();
    assertEquals(2, 2, outputStream.toString());
    assertThat(lines[0], containsString("playVideo needs implementation"));
    assertThat(lines[1], containsString("playVideo needs implementation"));
    assertThat(lines[2], containsString("Playing video: Funny Dogs"));
  }

  @Test
  public void testPlayVideoDontStopPreviousIfNonExistent() {
    videoPlayer.playVideo("amazing_cats_video_id");
    videoPlayer.playVideo("some_other_video");

    String[] lines = getOutputLines();
    assertEquals(2, lines.length, outputStream.toString());
    assertThat(lines[0], not(containsString("Stopping video: Amazing Cats")));
    assertThat(lines[1], containsString("playVideo needs implementation"));
  }

  @Test
  public void testStopVideo() {
    videoPlayer.playVideo("amazing_cats_video_id");
    videoPlayer.stopVideo();

    String[] lines = getOutputLines();
    assertEquals(2, lines.length, outputStream.toString());
    assertThat(lines[0], containsString("playVideo needs implementation"));
    assertThat(lines[1], containsString("stopVideo needs implementation"));
  }

  @Test
  public void testStopVideoTwice() {
    videoPlayer.playVideo("amazing_cats_video_id");
    videoPlayer.stopVideo();
    videoPlayer.stopVideo();

    String[] lines = getOutputLines();
    assertEquals(3, lines.length, outputStream.toString());
    assertThat(lines[0], containsString("playVideo needs implementation"));
    assertThat(lines[1], containsString("stopVideo needs implementation"));
    assertThat(lines[2],
        containsString("stopVideo needs implementation"));
  }

  @Test
  public void testStopVideoNothingPlaying() {
    videoPlayer.stopVideo();
    assertEquals(1, getOutputLines().length);
    assertThat(outputStream.toString(),
        containsString("stopVideo needs implementation"));
  }

  @Test
  public void testPlayRandomVideo() {
    videoPlayer.playRandomVideo();
    assertEquals(1, getOutputLines().length);
    assertThat(outputStream.toString(), matchesPattern("playRandomVideo needs implementation\r\n"));
  }

  @Test
  public void testPlayRandomVideoStopsPreviousVideo() {
    videoPlayer.playVideo("amazing_cats_video_id");
    videoPlayer.playRandomVideo();

    String[] lines = getOutputLines();
    assertEquals(2, lines.length, outputStream.toString());
    assertThat(lines[0], containsString("playVideo needs implementation"));
    assertThat(lines[1], containsString("playRandomVideo needs implementation"));
    assertThat(lines[2], matchesPattern(PLAYING_RANDOM_VIDEO_PATTERN));
  }

  @Test
  public void testShowPlaying() {
    videoPlayer.playVideo("amazing_cats_video_id");
    videoPlayer.showPlaying();

    String[] lines = getOutputLines();
    assertEquals(2, lines.length, outputStream.toString());
    assertThat(lines[0], containsString("playVideo needs implementation"));
    assertThat(lines[1],
        containsString("showPlaying needs implementation"));
  }

  @Test
  public void testShowNothingPlaying() {
    videoPlayer.showPlaying();
    assertEquals(1, getOutputLines().length);
    assertThat(outputStream.toString(), containsString("showPlaying needs implementation"));
  }

  @Test
  public void testPauseVideo() {
    videoPlayer.playVideo("amazing_cats_video_id");
    videoPlayer.pauseVideo();

    String[] lines = getOutputLines();
    assertEquals(2, lines.length, outputStream.toString());
    assertThat(lines[0], containsString("playVideo needs implementation"));
    assertThat(lines[1], containsString("pauseVideo needs implementation"));
  }

  @Test
  public void testPauseVideoShowVideo() {
    videoPlayer.playVideo("amazing_cats_video_id");
    videoPlayer.pauseVideo();
    videoPlayer.showPlaying();

    String[] lines = getOutputLines();
    assertEquals(3, lines.length, outputStream.toString());
    assertThat(lines[0], containsString("playVideo needs implementation"));
    assertThat(lines[1], containsString("pauseVideo needs implementation"));
    assertThat(lines[2], containsString(
        "showPlaying needs implementation"));
  }

  @Test
  public void testPauseVideoPlayVideo() {
    videoPlayer.playVideo("amazing_cats_video_id");
    videoPlayer.pauseVideo();
    videoPlayer.playVideo("amazing_cats_video_id");
    videoPlayer.showPlaying();

    String[] lines = getOutputLines();
    assertEquals(4, lines.length, outputStream.toString());
    assertThat(lines[0], containsString("playVideo needs implementation"));
    assertThat(lines[1], containsString("pauseVideo needs implementation"));
    assertThat(lines[2], containsString("playVideo needs implementation"));
    assertThat(lines[3], containsString("showPlaying needs implementation"));
    assertThat(lines[4], not(containsString("PAUSED")));
  }

  @Test
  public void testPauseAlreadyPausedVideo() {
    videoPlayer.playVideo("amazing_cats_video_id");
    videoPlayer.pauseVideo();
    videoPlayer.pauseVideo();

    String[] lines = getOutputLines();
    assertEquals(3, lines.length);
    assertThat(lines[0], containsString("playVideo needs implementation"));
    assertThat(lines[1], containsString("pauseVideo needs implementation"));
    assertThat(lines[2], containsString("pauseVideo needs implementation"));
  }
  
  @Test
  public void testPauseVideoNothingPlaying() {
    videoPlayer.pauseVideo();
    assertEquals(1, getOutputLines().length);
    assertThat(outputStream.toString(),
        containsString("pauseVideo needs implementation"));
  }

  @Test
  public void testContinueVideo() {
    videoPlayer.playVideo("amazing_cats_video_id");
    videoPlayer.pauseVideo();
    videoPlayer.continueVideo();

    String[] lines = getOutputLines();
    assertEquals(3, lines.length, outputStream.toString());
    assertThat(lines[0], containsString("playVideo needs implementation"));
    assertThat(lines[1], containsString("pauseVideo needs implementation"));
    assertThat(lines[2], containsString("continueVideo needs implementation"));
  }

  @Test
  public void testContinueVideoNotPaused() {
    videoPlayer.playVideo("amazing_cats_video_id");
    videoPlayer.continueVideo();

    String[] lines = getOutputLines();
    assertEquals(2, lines.length, outputStream.toString());
    assertThat(lines[1],
        containsString("continueVideo needs implementation"));
  }

  @Test
  public void testContinueVideoNothingPlaying() {
    videoPlayer.continueVideo();
    assertEquals(1, getOutputLines().length);
    assertThat(outputStream.toString(),
        containsString("continueVideo needs implementation"));
  }
}
