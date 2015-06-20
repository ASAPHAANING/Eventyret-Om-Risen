package com.haning.eventyromgris.states;

import com.badlogic.gdx.Gdx;
import static com.haning.eventyromgris.handlers.B2DVars.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.haning.eventyromgris.EventyretOmGrisen;
import com.haning.eventyromgris.entities.Player;
import com.haning.eventyromgris.handlers.B2DVars;
import com.haning.eventyromgris.handlers.GameStateManager;
import com.haning.eventyromgris.handlers.MyContactListener;
import com.haning.eventyromgris.handlers.MyInput;

import java.util.Vector;

/**
 * Created by haaning on 6/17/15.
 */
public class Play extends GameState {

    private World world;
    private Box2DDebugRenderer b2dr;
    private OrthographicCamera b2dcam;

    private MyContactListener cl;

    private TiledMap tileMap;
    private float tileSize;
    private OrthogonalTiledMapRenderer tmr;

    private BodyDef bdef;
    private FixtureDef fdef;
    private PolygonShape shape;

    private Player player;

    public Play(GameStateManager gsm)
    {

        super(gsm);

        // Setup Box2D stuff
        world = new World(new Vector2(0, -9.81f),true);
        cl = new MyContactListener();
        world.setContactListener(cl);
        b2dr = new Box2DDebugRenderer();

        bdef = new BodyDef();
        fdef = new FixtureDef();
        shape = new PolygonShape();

        // Create Player
        createPlayer();

        // Create Tiles
        createTiles();

        // Set up Box2D Cam
        b2dcam = new OrthographicCamera();
        b2dcam.setToOrtho(false, EventyretOmGrisen.V_WIDTH / PPM, EventyretOmGrisen.V_HEIGHT / PPM);





    }

    private void createTiles()
    {
        // Load tile map

        tileMap = new TmxMapLoader().load(String.valueOf(Gdx.files.internal("maps/level1.tmx")));
        tmr = new OrthogonalTiledMapRenderer(tileMap);

        tileSize = (Integer) tileMap.getProperties().get("tilewidth");

        TiledMapTileLayer layer;

        layer = (TiledMapTileLayer) tileMap.getLayers().get("Red");
        createLayer(layer,B2DVars.BIT_RED);

        layer = (TiledMapTileLayer) tileMap.getLayers().get("Green");
        createLayer(layer,B2DVars.BIT_GREEN);

        layer = (TiledMapTileLayer) tileMap.getLayers().get("Blue");
        createLayer(layer,B2DVars.BIT_BLUE);
    }

    private void createPlayer()
    {
        bdef.position.set(80 / PPM ,200 / PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.linearVelocity.set(1,0);

        Body body = world.createBody(bdef);
        shape.setAsBox(8 / PPM, 30 / PPM);
        fdef.shape = shape;
        fdef.filter.categoryBits = B2DVars.BIT_PLAYER;
        fdef.filter.maskBits = B2DVars.BIT_RED;
        body.createFixture(fdef).setUserData("player");

        // Create Foot Sensor
        shape.setAsBox(8 / PPM, 2 / PPM, new Vector2(0, -30 / PPM), 0);
        fdef.shape = shape;
        fdef.filter.categoryBits = B2DVars.BIT_PLAYER;
        fdef.filter.maskBits = B2DVars.BIT_RED;
        fdef.isSensor = true;
        body.createFixture(fdef).setUserData("foot");

        // Create Player
        player = new Player(body);
    }

    private void createLayer(TiledMapTileLayer layer, short bits)
    {
        // Go through all cells in the layer
        for(int row = 0; row < layer.getHeight(); row++)
        {
            for(int col = 0; col < layer.getWidth(); col++)
            {
                // Get cell
                TiledMapTileLayer.Cell cell = layer.getCell(col,row);

                // Check if cell exists
                if(cell == null) continue;
                if(cell.getTile() == null) continue;

                // Create a body & Fixture from the cell
                bdef.type = BodyDef.BodyType.StaticBody;
                bdef.position.set(
                        (col + 0.5f) * tileSize / PPM,
                        (row + 0.5f ) * tileSize / PPM
                );

                ChainShape cs = new ChainShape();
                Vector2[] v = new Vector2[3];
                v[0] = new Vector2(
                        -tileSize / 2 / PPM,
                        -tileSize / 2 / PPM
                );
                v[1] = new Vector2(
                        -tileSize / 2 / PPM,
                        tileSize / 2 / PPM
                );
                v[2] = new Vector2(
                        tileSize / 2 / PPM,
                        tileSize / 2 / PPM
                );
                cs.createChain(v);
                fdef.friction = 0;
                fdef.shape = cs;
                fdef.filter.categoryBits = bits;
                fdef.filter.maskBits = BIT_PLAYER;
                fdef.isSensor = false;
                world.createBody(bdef).createFixture(fdef);
            }
        }

    }

    @Override
    public void handleInput() {

        //Player Jump
      if(MyInput.isPressed(MyInput.BUTTON1))
      {
          if(cl.isPlayerOnGround())
          {
              player.getBody().applyForceToCenter(0, 200, true);
          }
      }

    }

    @Override
    public void update(float dt) {

        handleInput();

        player.update(dt);

        world.step(dt,6,2);

    }

    @Override
    public void render() {

        // Clear screen
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw Tilemap
        tmr.setView(cam);
        tmr.render();

        sb.setProjectionMatrix(cam.combined);
        player.render(sb);

        // Draw B2D world
        b2dr.render(world,b2dcam.combined);

    }

    @Override
    public void dispose() {

    }
}
