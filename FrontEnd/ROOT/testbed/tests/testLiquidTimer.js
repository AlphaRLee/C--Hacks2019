/*testLiquidTimer.js*/

//var psd;

//var particles = [];
//var stage = new Stage(document.getElementById('canvas'));
var gravity = new b2Vec2(0, 10);
var world = new b2World(gravity);
//var liquidSimulation = new LiquidSimulation(world);

var gateBody;
var gate;
var maxParticles = 10000;
var gateOpen = true;


function TestLiquidTimer() {
  camera.position.y = 2;
  camera.position.z = 3;
  var bd = new b2BodyDef;
  var ground = world.CreateBody(bd);



//Box to contain the simulation
/*
  var shape = new b2ChainShape;
  shape.vertices.push(new b2Vec2(-2, 4));
  shape.vertices.push(new b2Vec2(-2, 0));
  shape.vertices.push(new b2Vec2(2, 0));
  shape.vertices.push(new b2Vec2(2, 4));
  // shape.CreateLoop();
 ground.CreateFixtureFromShape(shape, 0.0);
*/
/*
var shape1 = new b2ChainShape;
shape1.vertices.push(new b2Vec2(-2, 4));
ground.CreateFixtureFromShape(shape1, 0.0);
*/
var shape = new b2ChainShape;
shape.vertices.push(new b2Vec2(-1.75, 0));
shape.vertices.push(new b2Vec2(2, 0));
shape.vertices.push(new b2Vec2(2, 2));
ground.CreateFixtureFromShape(shape, 0.0);



  bd = new b2BodyDef;
  var body = world.CreateBody(bd);


var gateSize = 0.2;
  /*  Outline of Dam - Rectangle*/
  /*
bd = new b2BodyDef;
body = world.CreateBody(bd);
shape = new b2EdgeShape;
shape.Set(new b2Vec2(-0.5, gateSize), new b2Vec2(-0.25, 3.75));
body.CreateFixtureFromShape(shape, 0.1);

bd = new b2BodyDef;
body = world.CreateBody(bd);
shape = new b2EdgeShape;
shape.Set(new b2Vec2(0.5,gateSize), new b2Vec2(0.25, 3.75));
body.CreateFixtureFromShape(shape, 0.1);

bd = new b2BodyDef;
body = world.CreateBody(bd);
shape = new b2EdgeShape;
shape.Set(new b2Vec2(-0.25,3.75), new b2Vec2(0.25, 3.75));
body.CreateFixtureFromShape(shape, 0.1);

bd = new b2BodyDef;
body = world.CreateBody(bd);
shape = new b2EdgeShape;
shape.Set(new b2Vec2(-0.5,gateSize), new b2Vec2(0.5, gateSize));
body.CreateFixtureFromShape(shape, 0.1);
*/
shape = new b2PolygonShape;
shape.vertices.push(new b2Vec2(-0.5, gateSize));
shape.vertices.push(new b2Vec2(-0.25, 3.75));
shape.vertices.push(new b2Vec2(0.25, 3.75));
shape.vertices.push(new b2Vec2(0.5, gateSize));
ground.CreateFixtureFromShape(shape, 0.0);


var vertices = [new b2Vec2(-0.5, gateSize), new b2Vec2(-0.25, 3.75), new b2Vec2(0.25, 3.75), new b2Vec2(0.5, gateSize)];
//var black = b2Color(0.5, 0.5, 0.5);
//DrawSolidPolygon(vertices, 4, black);





//Create funnel at top
/*
bd = new b2BodyDef;
body = world.CreateBody(bd);
shape = new b2EdgeShape;
shape.Set(new b2Vec2(-2,4), new b2Vec2(1.75, 3.5));
body.CreateFixtureFromShape(shape, 0.1);

bd = new b2BodyDef;
body = world.CreateBody(bd);
shape = new b2EdgeShape;
shape.Set(new b2Vec2(-2,4), new b2Vec2(-2, 10));
body.CreateFixtureFromShape(shape, 0.1);
*/

bd = new b2BodyDef;
body = world.CreateBody(bd);
shape = new b2EdgeShape;
shape.Set(new b2Vec2(2,2), new b2Vec2(20, 3));
body.CreateFixtureFromShape(shape, 0.1);

bd = new b2BodyDef;
body = world.CreateBody(bd);
shape = new b2EdgeShape;
shape.Set(new b2Vec2(8.5,2.55), new b2Vec2(2, 10));
body.CreateFixtureFromShape(shape, 0.1);


var psd;
psd = new b2ParticleSystemDef();
psd.radius = 0.025;
var particleSystem = world.CreateParticleSystem(psd);

closeGate();
openGate();

//
window.setInterval(resetParticles, 50);
// Continually create new particals
var rate = 0.1;
window.setInterval(createParticals, 500, particleSystem, rate);



}

function createParticals(particleSystem, rate){
  //echo "called";
if(particleSystem.GetParticleCount() < maxParticles){

  var shape = new b2PolygonShape;

  //shape.SetAsBoxXYCenterAngle(sizeOfXDimension, sizeofYDimension, centerPoint, ?);
  shape.SetAsBoxXYCenterAngle(0.1, rate, new b2Vec2(10, 3), 0);


  var pd = new b2ParticleGroupDef;
//  var pd = new b2ParticleDef;

  //change flags?
  pd.flags = b2_tensileParticle;
  pd.shape = shape;
  pd.color = new b2ParticleColor(0, 0, 255, 0);
  var something = particleSystem.CreateParticleGroup(pd);
}
//var someting = particleSystem.CreateParticle(pd);
//console.log(something);
/*
var graphic = new PIXI.Graphics();
      graphic.lineStyle(0);
      graphic.boundsPadding = 20;
      graphic.beginFill(color, 1);
      graphic.drawCircle(0, 0, 3);
      graphic.endFill();
      graphic.cacheAsBitmap = false;
      graphic.filters = [ blurFilter ];
texture = app.renderer.generateTexture(graphic)
var sprite = new PIXI.Sprite(texture);
*/
//particles.push(something);

}

function resetParticles(){
  //var particles = world.getParticles();
//  var particles = world.particleSystems[0].GetPositionBuffer();
//var particles = liquidSimulation.getParticles();
var particles = this.world.particleSystems[0].GetPositionBuffer();
var velocity = this.world.particleSystems[0].GetVelocityBuffer();
//console.log(particles[0]);
//console.log(velocity[0]);
/*
console.log("x");

console.log(particles[1]);
console.log(typeof particles[0]);
*/
//console.log(particles[0].x);
  for(var i = 0; i < particles.length; i+=2){
    var p = particles[i];
    if(p < -2 || p > 20){
      //x?
    //*  console.log(Math.random());
      particles[i] = 10 + Math.random();
      //y?
      particles[i+1] = 3 + Math.random();

      velocity[i] = 0;
      velocity[i+1] = 0;
    }

    //this works!!!
    //p.DestroyParticles();

  //  console.log(p);
/*
    if(p < 0){
    //  p.position.set(1, 3.7);
    //stage.remove(p, p.index);

    }else if(p.position.x < -0.25){
      ///  stage.remove(p, p.index);
    //    p.position.set(1, 3.7);
    }
    */

  }
}

/*
TestLiquidTimer.prototype.MouseDown = function(p){
  closeGate();
}

TestLiquidTimer.prototype.MouseUp = function(){
  openGate();
}
*/
function measureWater(){
  var particles = this.world.particleSystems[0].GetPositionBuffer();
  var particlesInDam = 0;
  for(var i = 0; i < particles.length; i+=2){
  //  var p = particles[i];
    if(particles[i] > 0 && particles[i] < 2){
      particlesInDam++;
    }

    //how many particals wide?
  }
  //window.alert(particlesInDam);
  var height = particlesInDam/1550;
  return height;
}

function closeGate(){
  var bd = new b2BodyDef;
  var body = world.CreateBody(bd);
  gateBody = body;
  var shape = new b2EdgeShape;
  shape.Set(new b2Vec2(0,0), new b2Vec2(0, 0.2));
  gate = body.CreateFixtureFromShape(shape, 0.1);
  gateOpen = false;
}


function openGate(){
  //DeleteFixtureFromShape(gate);
  gateBody.DestroyFixture(gate);
  gateOpen = true;
}

function setGate(height){
  if(gate != null){
    gateBody.DestroyFixture(gate);
  }
  var bd = new b2BodyDef;
  var body = world.CreateBody(bd);
  gateBody = body;
  var shape = new b2EdgeShape;
  shape.Set(new b2Vec2(0,height), new b2Vec2(0, 0.2));
  gate = body.CreateFixtureFromShape(shape, 0.1);

  gateOpen = false;

}

window.addEventListener('click', function () {
  if(gateOpen)
    closeGate();
  else {
    openGate();
  } });

/*
  window.addEventListener('keypress', function(){
    measureWater();
  })
  */
