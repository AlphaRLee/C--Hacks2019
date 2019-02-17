/*testLiquidTimer.js*/

//var psd;


function TestLiquidTimer() {
  camera.position.y = 2;
  camera.position.z = 3;
  var bd = new b2BodyDef;
  var ground = world.CreateBody(bd);

//Box to contain the simulation
  var shape = new b2ChainShape;
  shape.vertices.push(new b2Vec2(-2, 0));
  shape.vertices.push(new b2Vec2(2, 0));
  shape.vertices.push(new b2Vec2(2, 4));
  shape.vertices.push(new b2Vec2(-2, 4));
  shape.CreateLoop();
  ground.CreateFixtureFromShape(shape, 0.0);

/*
  psd = new b2ParticleSystemDef();
  psd.radius = 0.025;
  var particleSystem = world.CreateParticleSystem(psd);
  */

/*
  var psd = new b2ParticleSystemDef();
  psd.radius = 0.025;
  var particleSystem = world.CreateParticleSystem(psd);

//Sets the box as the center of the simulation
  shape = new b2PolygonShape;
  shape.SetAsBoxXYCenterAngle(2, 0.4, new b2Vec2(3, 3.6), 0);


  var pd = new b2ParticleGroupDef;
  pd.flags = b2_tensileParticle | b2_viscousParticle;
  pd.shape = shape;
  particleSystem.CreateParticleGroup(pd);
  */


  bd = new b2BodyDef;
  var body = world.CreateBody(bd);


/*
//Line to the left (Left and Right co-ords)
  shape = new b2EdgeShape;
  shape.Set(new b2Vec2(-2, 3.2), new b2Vec2(-1.2, 3.2));
  body.CreateFixtureFromShape(shape, 0.1);

//Line to the right
  bd = new b2BodyDef;
  body = world.CreateBody(bd);
  shape = new b2EdgeShape;
  shape.Set(new b2Vec2(-1.1, 3.2), new b2Vec2(2, 3.2));
  body.CreateFixtureFromShape(shape, 0.1);

//Line to the left and Vertical
  bd = new b2BodyDef;
  body = world.CreateBody(bd);
  shape = new b2EdgeShape;
  shape.Set(new b2Vec2(-1.2, 3.2), new b2Vec2(-1.2, 2.8));
  body.CreateFixtureFromShape(shape, 0.1);

//Line to the right and Vertical
  bd = new b2BodyDef;
  body = world.CreateBody(bd);
  shape = new b2EdgeShape;
  shape.Set(new b2Vec2(-1.1, 6), new b2Vec2(-1.1, 2.8));
   body.CreateFixtureFromShape(shape, 0.1);

  bd = new b2BodyDef;
  body = world.CreateBody(bd);
  shape = new b2EdgeShape;
  shape.Set(new b2Vec2(-1.6, 2.4), new b2Vec2(0.8, 2));
  body.CreateFixtureFromShape(shape, 0.1);

  bd = new b2BodyDef;
  body = world.CreateBody(bd);
  shape = new b2EdgeShape;
  shape.Set(new b2Vec2(1.6, 1.6), new b2Vec2(-0.8, 1.2));
  body.CreateFixtureFromShape(shape, 0.1);


  bd = new b2BodyDef;
  body = world.CreateBody(bd);
  shape = new b2EdgeShape;
  shape.Set(new b2Vec2(-1.2, 0.8), new b2Vec2(-1.2, 0));
  body.CreateFixtureFromShape(shape, 0.1);

  bd = new b2BodyDef;
  body = world.CreateBody(bd);
  shape = new b2EdgeShape;
  shape.Set(new b2Vec2(-0.4, 0.8), new b2Vec2(-0.4, 0));
  body.CreateFixtureFromShape(shape, 0.1);


  bd = new b2BodyDef;
  body = world.CreateBody(bd);
  shape = new b2EdgeShape;
  shape.Set(new b2Vec2(0.4, 0.8), new b2Vec2(0.4, 0));
  body.CreateFixtureFromShape(shape, 0.1);

  bd = new b2BodyDef;
  body = world.CreateBody(bd);
  shape = new b2EdgeShape;
  shape.Set(new b2Vec2(1.2, 0.8), new b2Vec2(1.2, 0));
  body.CreateFixtureFromShape(shape, 0.1);
  */

  /*  Outline of Dam - Rectangle*/
bd = new b2BodyDef;
body = world.CreateBody(bd);
shape = new b2EdgeShape;
shape.Set(new b2Vec2(-0.25,0.1), new b2Vec2(-0.25, 3));
body.CreateFixtureFromShape(shape, 0.1);

bd = new b2BodyDef;
body = world.CreateBody(bd);
shape = new b2EdgeShape;
shape.Set(new b2Vec2(0.25,0.1), new b2Vec2(0.25, 3));
body.CreateFixtureFromShape(shape, 0.1);

bd = new b2BodyDef;
body = world.CreateBody(bd);
shape = new b2EdgeShape;
shape.Set(new b2Vec2(-0.25,3), new b2Vec2(0.25, 3));
body.CreateFixtureFromShape(shape, 0.1);

bd = new b2BodyDef;
body = world.CreateBody(bd);
shape = new b2EdgeShape;
shape.Set(new b2Vec2(-0.25,0.1), new b2Vec2(0.25, 0.1));
body.CreateFixtureFromShape(shape, 0.1);

//Gate
/*
bd = new b2BodyDef;
body = world.CreateBody(bd);
shape = new b2EdgeShape;
shape.Set(new b2Vec2(-0.25,0), new b2Vec2(-0.25, 0.25));
body.CreateFixtureFromShape(shape, 0.1);
*/

//Direct liquid to right
/*
bd = new b2BodyDef;
body = world.CreateBody(bd);
shape = new b2EdgeShape;
shape.Set(new b2Vec2(-2,4), new b2Vec2(-0.25, 3));
body.CreateFixtureFromShape(shape, 0.1);
*/

var psd;
psd = new b2ParticleSystemDef();
psd.radius = 0.025;
var particleSystem = world.CreateParticleSystem(psd);

// Continually create new particals
window.setInterval(createParticals, 500, particleSystem);

/*
var i;
for(i = 0; i < 10000000; i++){

}

shape = new b2PolygonShape;
shape.SetAsBoxXYCenterAngle(2, 0.4, new b2Vec2(1, 1.2), 0);


var pd = new b2ParticleGroupDef;
pd.flags = b2_tensileParticle | b2_viscousParticle;
pd.shape = shape;
particleSystem.CreateParticleGroup(pd);
*/


}

function createParticals(particleSystem){
  //echo "called";





  var shape = new b2PolygonShape;
  shape.SetAsBoxXYCenterAngle(0.1, 0.1, new b2Vec2(1, 3.5), 0);


  var pd = new b2ParticleGroupDef;
  pd.flags = b2_tensileParticle | b2_viscousParticle;
  pd.shape = shape;
  pd.color = new b2ParticleColor(0, 0, 255, 0);
  particleSystem.CreateParticleGroup(pd);


}
