/*
 *  Copyright 2025, TeamDev. All rights reserved.
 *
 *  Redistribution and use in source and/or binary forms, with or without
 *  modification, must retain the above copyright notice and the following
 *  disclaimer.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 *  "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 *  LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 *  A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 *  OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 *  SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 *  LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 *  DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 *  THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 *  OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.teamdev.jxbrowser.examples.javafx;

import static com.teamdev.jxbrowser.engine.RenderingMode.OFF_SCREEN;

import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.view.javafx.BrowserView;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * This example demonstrates how to display JavaFX BrowserView in TabPane.
 */
public final class BrowserViewInTabPane extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        var engine = Engine.newInstance(OFF_SCREEN);

        var browserOne = engine.newBrowser();
        browserOne.navigation()
                  .loadUrl("https://webglsamples.org/dynamic-cubemap/dynamic-cubemap.html");
        var viewOne = BrowserView.newInstance(browserOne);

        var tabOne = new Tab("Browser One");
        tabOne.setContent(viewOne);

        var browserTwo = engine.newBrowser();
        browserTwo.navigation().loadUrl("https://html5test.teamdev.com/");
        var viewTwo = BrowserView.newInstance(browserTwo);

        var tabTwo = new Tab("Browser Two");
        tabTwo.setContent(viewTwo);

        var tabPane = new TabPane();
        tabPane.getTabs().add(tabOne);
        tabPane.getTabs().add(tabTwo);

        var root = new Group();
        var scene = new Scene(root, 700, 500);

        var pane = new BorderPane();
        pane.prefHeightProperty().bind(scene.heightProperty());
        pane.prefWidthProperty().bind(scene.widthProperty());
        pane.setCenter(tabPane);

        root.getChildren().add(pane);

        primaryStage.setTitle("Browser View In Tab Pane");
        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> engine.close());
    }
}
