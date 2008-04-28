import geomerative.*;
import processing.opengl.*;

// Declare the objects we are going to use, so that they are accesible from setup() and from draw()
RFont f;
RGroup grp;
RMatrix mat;

void setup(){
  // Initilaize the sketch
  size(600,400,OPENGL);
  frameRate(24);

  // VERY IMPORTANT: Allways initialize the library in the setup
  RGeomerative.init(this);

  // Choice of colors
  background(255);
  fill(255,102,0);
  stroke(0);
  
  //  Load the font file we want to use (the file must be in the data folder in the sketch floder), with the size 60 and the alignment CENTER
  f = new RFont("FreeSans.ttf", 72, RFont.CENTER);
  grp = f.toGroup("Hola mundo!");

  // Enable smoothing
  smooth();
  
  // Define a rotation of PI/20 around the center of the first letter
  mat = new RMatrix();
  // To get the center of the first letter we must access the first element on the group
  RPoint centerOfFirstLetter = grp.elements[0].getCenter();
  mat.rotate(PI/20,centerOfFirstLetter);
}

void draw(){
  // Clean frame
  background(255);
  
  // Set the origin to draw in the middle of the sketch
  translate(width/2, height/2);
  
  // Transform at each frame the first letter with the transformation we defined before
  grp.elements[0].transform(mat);
  
  // Draw the group of shapes representing "Hola mundo!" on the PGraphics canvas g (which is the default canvas of the sketch)
  grp.draw();
}
