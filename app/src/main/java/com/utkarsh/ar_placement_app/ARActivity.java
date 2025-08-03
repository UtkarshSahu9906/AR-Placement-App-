package com.utkarsh.ar_placement_app;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.ar.core.Anchor;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.Color;
import com.google.ar.sceneform.rendering.MaterialFactory;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.ShapeFactory;
import com.google.ar.sceneform.ux.TransformableNode;

public class ARActivity extends AppCompatActivity {
    private Fragment arFragment;
    private ModelRenderable drillRenderable;
    private String selectedDrill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aractivity);

        selectedDrill = getIntent().getStringExtra("selected_drill");
        arFragment = getSupportFragmentManager().findFragmentById(R.id.ar_fragment);

        buildDrillModel();
        setupTapListener();
    }

    private void buildDrillModel() {
        MaterialFactory.makeOpaqueWithColor(this, new Color(android.graphics.Color.GRAY))
                .thenAccept(material -> {
                    // Create different shapes based on drill selection
                    if (selectedDrill.contains("Cone")) {
                        drillRenderable = ShapeFactory.makeCube(
                                new Vector3(0.1f, 0.2f, 0.1f),
                                Vector3.zero(),
                                material);
                    } else if (selectedDrill.contains("Red")) {
                        material.setFloat3("color", new Color(android.graphics.Color.RED));
                        drillRenderable = ShapeFactory.makeCube(
                                new Vector3(0.15f, 0.15f, 0.15f),
                                Vector3.zero(),
                                material);
                    } else {
                        material.setFloat3("color", new Color(android.graphics.Color.BLUE));
                        drillRenderable = ShapeFactory.makeCube(
                                new Vector3(0.15f, 0.15f, 0.15f),
                                Vector3.zero(),
                                material);
                    }
                });
    }

    private void setupTapListener() {
//        arFragment.setEnterTransition((hitResult, plane, motionEvent) -> {
//            if (drillRenderable == null) return;
//
//            // Create anchor
////Anchor anchor = hitResult.createAnchor();
////            anchorNode.setParent(arFragment.getArSceneView().getScene());
//
//            // Create and attach node
//            TransformableNode node = new TransformableNode(arFragment.get());
//            node.setParent(anchorNode);
//            node.setRenderable(drillRenderable);
//            node.select();
//        });
    }
}