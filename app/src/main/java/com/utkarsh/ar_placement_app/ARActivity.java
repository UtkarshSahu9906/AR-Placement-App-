package com.utkarsh.ar_placement_app;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.ar.core.Anchor;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.MaterialFactory;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.ShapeFactory;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

public class ARActivity extends AppCompatActivity {
    private ArFragment arFragment;
    private ModelRenderable drillRenderable;
    private String selectedDrill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aractivity);

        selectedDrill = getIntent().getStringExtra("selected_drill");
        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.ar_fragment);

        buildDrillModel();
    }

    private void buildDrillModel() {
        MaterialFactory.makeOpaqueWithColor(this, new com.google.ar.sceneform.rendering.Color(Color.GRAY))
                .thenAccept(material -> {
                    if (selectedDrill != null && selectedDrill.contains("Cone")) {
                        drillRenderable = ShapeFactory.makeCylinder(
                                0.05f, 0.2f,
                                Vector3.zero(),



                                material);
                    } else if (selectedDrill != null && selectedDrill.contains("Red")) {
                        material.setFloat3("color", new com.google.ar.sceneform.rendering.Color(Color.RED));
                        drillRenderable = ShapeFactory.makeCube(
                                new Vector3(0.15f, 0.15f, 0.15f),
                                Vector3.zero(),
                                material);
                    } else {
                        material.setFloat3("color", new com.google.ar.sceneform.rendering.Color(Color.BLUE));
                        drillRenderable = ShapeFactory.makeSphere(
                                0.15f,                 // radius
                                Vector3.zero(),       // center
                                material              // material
                        );

                    }
                    setupTapListener();
                });
    }

    private void setupTapListener() {
        arFragment.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {
            if (drillRenderable == null) return;

            Anchor anchor = hitResult.createAnchor();
            AnchorNode anchorNode = new AnchorNode(anchor);
            anchorNode.setParent(arFragment.getArSceneView().getScene());

            TransformableNode node = new TransformableNode(arFragment.getTransformationSystem());
            node.setParent(anchorNode);
            node.setRenderable(drillRenderable);
            node.select();
        });
    }
}