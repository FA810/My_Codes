// -----------------------------------
// codice scritto da Fabio Rizzello
// -----------------------------------
/**********************
INCLUSIONE LIBRERIE
***********************/
#include <GL/glut.h>    // Header File For The GLUT Library
#include <GL/gl.h>      // Header File For The OpenGL32 Library
#include <GL/glu.h>     // Header File For The GLu32 Library
#include <GL/glx.h>     // Header file fot the glx libraries.
#include <unistd.h>     // Header file for sleeping.
#include <stdio.h>      // Header file for standard file i/o.
#include <stdlib.h>     // Header file for malloc/free.
#include <math.h>       // Header file for trigonometric functions.
// inclusione costanti
#include "./h/costanti.h"

/**********************
DICHIARAZIONE VARIABILI
***********************/

// GLUT window
int window;

// --- variabili per tabella sudoku
// rimaste vuote
int restanti=81;
// risultati
int res[9][9];		
// durante il gioco
int temp[9][9];
// iniziali nel gioco
int init[9][9];
// x e y correnti (coordinate mondo)
GLint curx=0;
GLint cury=0;
// x e y selezionate (coordinate tabella)
GLint selx=4;
GLint sely=4;


// ---variabili display list
// casella
GLuint cube;
// cornice tabella
GLuint stripe;
// riquadro casella selezionata
GLuint quad;

// ---variabili luci
float LightPos[] = { 0.0f, 3.0f, -12.0f, 1.0f};			// Light Position
float LightAmb[] = { 0.2f, 0.2f, 0.2f, 1.0f};			// Ambient Light Values
float LightDif[] = { 0.5f, 0.5f, 1.0f, 1.0f};			// Diffuse Light Values
float LightSpc[] = {1.0f, 1.0f, 0.4f, 1.0f};			// Specular Light Values

float MatAmb[] = {0.9f, 0.9f, 0.9f, 1.0f};			// Material - Ambient Values
float MatDif[] = {0.9f, 0.6f, 0.9f, 1.0f};			// Material - Diffuse Values
float MatSpc[] = {0.9f, 0.9f, 0.7f, 1.0f};			// Material - Specular Values
float MatShn[] = {1.0f};					// Material - Shininess

// ---variabili controlli avanzati
// filtro textures
GLuint filter;
// abilita blending
GLuint  blend=1;
// cambia vista                  
int  view;
// abilita luce
int light;
// abilita sfondo
GLuint sfondo;
// abilita credits
GLuint  author;
// abilita mirino
GLuint mirino;
// abilita minimap
GLuint minimap;    

// ---variabili controllo movimento
GLfloat xrot;            // x rotation
GLfloat yrot;            // y rotation
GLfloat xspeed;          // x rotation speed
GLfloat yspeed;          // y rotation speed

GLfloat walkbiasangle;
float xpos, zpos;
GLfloat altezza=0.0f;                       // depth into the screen.

// ---variabili textures
struct Image {
    unsigned long sizeX;
    unsigned long sizeY;
    char *data;
};
typedef struct Image Image; 
// texture numeri per caselle (3 livelli di definizione)
GLuint  texture[9][3];
// texture per credits
GLuint foto;
// texture per environment mapping
GLuint background;
// altre textures
GLuint rest;
GLuint zero;
GLuint menut;

/**********************
FUNZIONI
***********************/
// immagini bmp, solita funzione di caricamento
int ImageLoad(char *filename, Image *image) {
    FILE *file;
    unsigned long size;                 // size of the image in bytes.
    unsigned long i;                    // standard counter.
    unsigned short int planes;          // number of planes in image (must be 1) 
    unsigned short int bpp;             // number of bits per pixel (must be 24)
    char temp;                          // temporary color storage for bgr-rgb conversion.
    // make sure the file is there.
    if ((file = fopen(filename, "rb"))==NULL){
	printf("File Not Found : %s\n",filename);
	return 0;
    }
    // seek through the bmp header, up to the width/height:
    fseek(file, 18, SEEK_CUR);
    // read the width
    if ((i = fread(&image->sizeX, 4, 1, file)) != 1) {
	printf("Error reading width from %s.\n", filename);
	return 0;
    }
    //printf("Width of %s: %lu\n", filename, image->sizeX);
    // read the height 
    if ((i = fread(&image->sizeY, 4, 1, file)) != 1) {
	printf("Error reading height from %s.\n", filename);
	return 0;
    }
    //printf("Height of %s: %lu\n", filename, image->sizeY);
    // calculate the size (assuming 24 bits or 3 bytes per pixel).
    size = image->sizeX * image->sizeY * 3;
    // read the planes
    if ((fread(&planes, 2, 1, file)) != 1) {
	printf("Error reading planes from %s.\n", filename);
	return 0;
    }
    if (planes != 1) {
	printf("Planes from %s is not 1: %u\n", filename, planes);
	return 0;
    }
    // read the bpp
    if ((i = fread(&bpp, 2, 1, file)) != 1) {
	printf("Error reading bpp from %s.\n", filename);
	return 0;
    }
    if (bpp != 24) {
	printf("Bpp from %s is not 24: %u\n", filename, bpp);
	return 0;
    }
    // seek past the rest of the bitmap header.
    fseek(file, 24, SEEK_CUR);
    // read the data. 
    image->data = (char *) malloc(size);
    if (image->data == NULL) {
	printf("Error allocating memory for color-corrected image data");
	return 0;	
    }
    if ((i = fread(image->data, size, 1, file)) != 1) {
	printf("Error reading image data from %s.\n", filename);
	return 0;
    }
    for (i=0;i<size;i+=3) { // reverse all of the colors. (bgr -> rgb)
	temp = image->data[i];
	image->data[i] = image->data[i+2];
	image->data[i+2] = temp;
    }    
    return 1;
}
    
// Load Bitmaps And Convert To Textures
void LoadGLTextures() {	
	int i; 
	Image *image1;
	// genero texture numeri per caselle
	glGenTextures(27, &texture[0][0]);
	for(i=1;i<10;i++){
		image1 = (Image *) malloc(sizeof(Image));
		if (image1 == NULL) {
			printf("Error allocating space for image");
			exit(0);
		}
		switch(i){
      			case 1:	ImageLoad("numbers/uno.bmp", image1); break;
      			case 2:	ImageLoad("numbers/due.bmp", image1); break;
      			case 3:	ImageLoad("numbers/tre.bmp", image1); break;
      			case 4:	ImageLoad("numbers/qua.bmp", image1); break;
      			case 5:	ImageLoad("numbers/cin.bmp", image1); break;        			
        		case 6:	ImageLoad("numbers/sei.bmp", image1); break;       				
        		case 7:	ImageLoad("numbers/set.bmp", image1); break;        		        				
        		case 8:	ImageLoad("numbers/ott.bmp", image1); break;        			        			
        		case 9:	ImageLoad("numbers/nov.bmp", image1); break;	
    		}
    		// livello grezzo
    		glBindTexture(GL_TEXTURE_2D, texture[i-1][0]);
    		glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MAG_FILTER,GL_NEAREST);
    		glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MIN_FILTER,GL_NEAREST);
    		glTexImage2D(GL_TEXTURE_2D, 0, 3, image1->sizeX, image1->sizeY, 0, GL_RGB, GL_UNSIGNED_BYTE, image1->data);    		
    		// livello linear
	    	glBindTexture(GL_TEXTURE_2D, texture[i-1][1]);   
	    	glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MAG_FILTER,GL_LINEAR); 
	    	glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MIN_FILTER,GL_LINEAR); 
	    	glTexImage2D(GL_TEXTURE_2D, 0, 3, image1->sizeX, image1->sizeY, 0, GL_RGB, GL_UNSIGNED_BYTE, image1->data);
		// livello mipmap
		glBindTexture(GL_TEXTURE_2D, texture[i-1][2]);   
	    	glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MAG_FILTER,GL_LINEAR); 
	    	glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MIN_FILTER,GL_LINEAR_MIPMAP_NEAREST); 
	    	glTexImage2D(GL_TEXTURE_2D, 0, 3, image1->sizeX, image1->sizeY, 0, GL_RGB, GL_UNSIGNED_BYTE, image1->data);
		gluBuild2DMipmaps(GL_TEXTURE_2D, 3, image1->sizeX, image1->sizeY, GL_RGB, GL_UNSIGNED_BYTE, image1->data); 
  		// libera memoria
  		if (image1){
    			if (image1->data){
      				free(image1->data);
    			}
    			free(image1);
  		}  	
  	}
  	// genero texture credits
  	glGenTextures(1, &foto);
  	image1 = (Image *) malloc(sizeof(Image));
	if (image1 == NULL) {
		printf("Error allocating space for image");
		exit(0);
	}
	ImageLoad("author/fabio.bmp", image1);
	glBindTexture(GL_TEXTURE_2D, foto);   
	glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MIN_FILTER,GL_LINEAR); 
	glTexImage2D(GL_TEXTURE_2D, 0, 3, image1->sizeX, image1->sizeY, 0, GL_RGB, GL_UNSIGNED_BYTE, image1->data);
	if (image1){
    		if (image1->data){
      			free(image1->data);      
    		}
    		free(image1);                  
  	} 
  	// genero texture backgrounds  	
  	glGenTextures(1, &background);
  	image1 = (Image *) malloc(sizeof(Image));
	if (image1 == NULL) {
		printf("Error allocating space for image");
		exit(0);
	}
	ImageLoad("background/chicagonight.bmp", image1);
	glBindTexture(GL_TEXTURE_2D, background);   
	glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MAG_FILTER,GL_LINEAR);
	glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MIN_FILTER,GL_LINEAR);  
	glTexImage2D(GL_TEXTURE_2D, 0, 3, image1->sizeX, image1->sizeY, 0, GL_RGB, GL_UNSIGNED_BYTE, image1->data);
	if (image1){
    		if (image1->data){
      			free(image1->data);        
    		}
    		free(image1);                  
  	}
  	//texture restanti
  	glGenTextures(1, &rest);
  	image1 = (Image *) malloc(sizeof(Image));
	if (image1 == NULL) {
		printf("Error allocating space for image");
		exit(0);
	}
	ImageLoad("numbers/restanti.bmp", image1);
	glBindTexture(GL_TEXTURE_2D, rest);   
	glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MAG_FILTER,GL_LINEAR);
	glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MIN_FILTER,GL_LINEAR/*_MIPMAP_NEAREST*/);
	glTexImage2D(GL_TEXTURE_2D, 0, 3, image1->sizeX, image1->sizeY, 0, GL_RGB, GL_UNSIGNED_BYTE, image1->data);
	//gluBuild2DMipmaps(GL_TEXTURE_2D, 3, image1->sizeX, image1->sizeY, GL_RGB, GL_UNSIGNED_BYTE, image1->data); 
	if (image1){
    		if (image1->data){
      			free(image1->data);        
    		}
    		free(image1);                  
  	}
  	// texture zero... (aggiunta dopo)
  	glGenTextures(1, &zero);
  	image1 = (Image *) malloc(sizeof(Image));
	if (image1 == NULL) {
		printf("Error allocating space for image");
		exit(0);
	}
	ImageLoad("numbers/zero.bmp", image1);
	glBindTexture(GL_TEXTURE_2D, zero);   
	glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MAG_FILTER,GL_LINEAR);
	glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MIN_FILTER,GL_LINEAR);  
	glTexImage2D(GL_TEXTURE_2D, 0, 3, image1->sizeX, image1->sizeY, 0, GL_RGB, GL_UNSIGNED_BYTE, image1->data);
	if (image1){
    		if (image1->data){
      			free(image1->data);        
    		}
    		free(image1);                  
  	}
  	// texture menu... (aggiunta dopo)
  	glGenTextures(1, &menut);
  	image1 = (Image *) malloc(sizeof(Image));
	if (image1 == NULL) {
		printf("Error allocating space for image");
		exit(0);
	}
	ImageLoad("numbers/menu.bmp", image1);
	glBindTexture(GL_TEXTURE_2D, menut);   
	glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MAG_FILTER,GL_LINEAR);
	glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MIN_FILTER,GL_LINEAR);  
	glTexImage2D(GL_TEXTURE_2D, 0, 3, image1->sizeX, image1->sizeY, 0, GL_RGB, GL_UNSIGNED_BYTE, image1->data);
	if (image1){
    		if (image1->data){
      			free(image1->data);        
    		}
    		free(image1);                  
  	}   		   		
}

// funzione per leggere il file di testo
void readstr(FILE *f, char *string)
{
    do {
	fgets(string, 255, f); // read the line
    } while ((string[0] == '/') || (string[0] == '\n'));
    return;
}

// prepara la tebella del sudoku, lettura da file di testo
void SetupTabella(){
	int i,j;
	int val,show;
   	FILE *filein;
   	char oneline[6];
   
   	filein = fopen("game/tab1.txt", "rt"); // Apre il File
   
   	for (i = 0; i < 9; i++) {
   		for (j = 0; j < 9; j++) {	
	    		readstr(filein,oneline);
	    		sscanf(oneline, "%d %d ", &val, &show);
	    		res[i][j]=val;
	    		if(show==1){
	    			restanti--;
	    			init[i][j]=val;
	    			temp[i][j]=val;
	    		}
	    		else{
	    			temp[i][j]=0;
	    			init[i][j]=0;
	    		}    
		}
    	} 
    	
    	/* STAMPA TABELLE */
    	/*
    	for (i = 0; i < 9; i++) {
   		for (j = 0; j < 9; j++) {	
	    		printf(" %d",res[i][j]);	    		    
		}
		printf("\n");
    	}  
    	printf("\n");printf("\n");
    	for (i = 0; i < 9; i++) {
   		for (j = 0; j < 9; j++) {	
	    		printf(" %d",temp[i][j]);	    		    
		}
		printf("\n");
    	} 
    	*/    	   	   
    	fclose(filein);
    	return;   
}

// dalla posizione nel mondo, ricava posizione nella tabella;
// valido per righe e per colonne.
int calcolaIndice(float i){
	int inx;
	inx=i+12;
    	switch(inx){
    		case 0: inx=0; break;
    		case 3: inx=1; break;
    		case 6: inx=2; break;
    		case 9: inx=3; break;
    		case 12: inx=4; break;
    		case 15: inx=5; break;
    		case 18: inx=6; break;
    		case 21: inx=7; break;
    		case 24: inx=8; break;    			
    	}
    	return (inx);
}

// toglie numero da una casella
void resetCasella(){
	if(init[sely][selx]==0){
		if(temp[sely][selx]!=0){
			temp[sely][selx]=0;
			restanti++;
			//printf("restanti+: %d\n",restanti);
		}
		temp[sely][selx]=0;
	}	
}

// REGOLAMENTO DEL SUDOKU
// controlla regolarita' nel riquadro
int controllaRiquadro(int selex, int seley, int val){
	
	int xmin,xmax,ymin,ymax;
	int x,y;
	int resp=1;
		
	switch(selex){
		case 0:
		case 1:
		case 2:xmin=0; xmax=2;break;
		
		case 3:
		case 4:
		case 5:xmin=3; xmax=5;break;
		
		case 6:
		case 7:
		case 8:xmin=6; xmax=8;break;
	};
		
	switch(seley){
		case 0:
		case 1:
		case 2:ymin=0; ymax=2;break;
		
		case 3:
		case 4:
		case 5:ymin=3; ymax=5;break;
		
		case 6:
		case 7:
		case 8:ymin=6; ymax=8;break;
	};
		
	for(x=xmin;((x<xmax+1)&&(resp!=0));x++){
		for(y=ymin;((y<ymax+1)&&(resp!=0));y++){		
			//printf("\n riquadro %d %d    %d %d    %d %d    %d %d    %d \n",selex,seley,x,y,xmin,xmax,ymin,ymax,temp[y][x]);
			if((y!=seley)||(x!=selex)){
				if(temp[y][x]==val){
					resp=0;
				}
			}
			//else printf(" trovato!\n ");		
		}		
	}
	return resp;
}

// REGOLAMENTO DEL SUDOKU
// controlla regolarita' nella colonna
int controllaColonna(int sel, int i){
	int indice;
	int resp=1;
	for(indice=0;((indice<9)&&(resp!=0));indice++){
		if((indice!=sely)&&(temp[indice][sel]==i)){
			resp=0;
		}		
	}
	return resp;
}

// REGOLAMENTO DEL SUDOKU
// controlla regolarita' nella riga
int controllaRiga(int sel, int i){
	int indice;
	int resp=1;
	for(indice=0;((indice<9)&&(resp!=0));indice++){
		if((indice!=selx)&&(temp[sel][indice]==i)){
			resp=0;
		}
	}	
	return resp;
}

// REGOLAMENTO DEL SUDOKU
// tenta di scrivere la casella dopo i dovuti controlli
void scriviCasella(int i){
	
	if(controllaColonna(selx,i)!=0){
		if(controllaRiga(sely,i)!=0){
			if(controllaRiquadro(selx,sely,i)!=0){
				if(init[sely][selx]==0){
					if(temp[sely][selx]==0){
						restanti--;
						//printf("restanti-: %d\n",restanti);
					}	
					temp[sely][selx]=i;					
				}	
			}		
			else{resetCasella();}
		}	
		else{resetCasella();}		
	}
	else{resetCasella();}		
	return;
}


// costruzione del display list
GLvoid BuildList(){
	// 3 oggetti in display list
	cube = glGenLists(3);
	// un cubo senza faccia frontale per le caselle			
	glNewList(cube, GL_COMPILE);	
	glColor3f(0.9f,0.9f,0.9f);	
	// retro
	glBegin(GL_QUADS);			
    	glNormal3f(0.0f, 0.0f, -1.0f);		
	glVertex3f(-1.0f, -1.0f, -1.0f);	
	glVertex3f(-1.0f,  1.0f, -1.0f);	
	glVertex3f( 1.0f,  1.0f, -1.0f);	
	glVertex3f( 1.0f, -1.0f, -1.0f);	
	// bottom
	glNormal3f(0.0f, -1.0f, 0.0f);	
	glVertex3f(-1.0f, -1.0f, -1.0f);	
	glVertex3f( 1.0f, -1.0f, -1.0f);	
	glVertex3f( 1.0f, -1.0f,  1.0f);	
	glVertex3f(-1.0f, -1.0f,  1.0f);	
	// destra
	glNormal3f(1.0f, 0.0f, 0.0f);	
	glVertex3f( 1.0f, -1.0f, -1.0f);	
	glVertex3f( 1.0f,  1.0f, -1.0f);	
	glVertex3f( 1.0f,  1.0f,  1.0f);	
	glVertex3f( 1.0f, -1.0f,  1.0f);	
	// sinistra
	glNormal3f(-1.0f, 0.0f, 0.0f);	
	glVertex3f(-1.0f, -1.0f, -1.0f);	
	glVertex3f(-1.0f, -1.0f,  1.0f);	
	glVertex3f(-1.0f,  1.0f,  1.0f);	
	glVertex3f(-1.0f,  1.0f, -1.0f);	    	    
	// top
	glNormal3f(0.0f, 1.0f, 0.0f);	
	glVertex3f(-1.0f,  1.0f, -1.0f);	
	glVertex3f(-1.0f,  1.0f,  1.0f);	
	glVertex3f( 1.0f,  1.0f,  1.0f);	
	glVertex3f( 1.0f,  1.0f, -1.0f);	
	glEnd();
	glEndList();
		
	stripe = cube+1;
	// striscia per cornice            
	glNewList(stripe, GL_COMPILE);		
	glBegin(GL_QUADS);
	glNormal3f(0.0f, 0.0f, 1.0f);	
	glVertex3f(0.1f, 13.4f, 0.0f);
	glVertex3f(-0.1f, 13.4f, 0.0f);
	glVertex3f(-0.1f, -13.4f, 0.0f);
	glVertex3f(0.1f, -13.4f, 0.0f);
	glEnd();
	glEndList();	
	
	quad = cube+2;           
	// riquadro casella selezionata
	glNewList(quad, GL_COMPILE);       	
	glColor3f(1.0f,0.0f,0.0f);
	glBegin(GL_QUADS);
	glNormal3f(0.0f, 0.0f, 1.0f);	
	glVertex3f( 1.3f, 1.1f, 0.0f);
	glVertex3f( 1.1f, 1.1f, 0.0f);
	glVertex3f( 1.1f, -1.1f, 0.0f);
	glVertex3f( 1.3f, -1.1f, 0.0f);
	glEnd();	
	glBegin(GL_QUADS);
	glNormal3f(0.0f, 0.0f, 1.0f);	
	glVertex3f( 1.3f, 1.3f, 0.0f);
	glVertex3f(-1.3f, 1.3f, 0.0f);
	glVertex3f(-1.3f, 1.1f, 0.0f);
	glVertex3f( 1.3f, 1.1f, 0.0f);
	glEnd();	
	glBegin(GL_QUADS);
	glNormal3f(0.0f, 0.0f, 1.0f);	
	glVertex3f(-1.1f, 1.1f, 0.0f);
	glVertex3f(-1.3f, 1.1f, 0.0f);
	glVertex3f(-1.3f, -1.1f, 0.0f);
	glVertex3f(-1.1f, -1.1f, 0.0f);
	glEnd();	
	glBegin(GL_QUADS);
	glNormal3f(0.0f, 0.0f, 1.0f);	
	glVertex3f( 1.3f,-1.1f, 0.0f);
	glVertex3f(-1.3f,-1.1f, 0.0f);
	glVertex3f(-1.3f,-1.3f, 0.0f);
	glVertex3f( 1.3f,-1.3f, 0.0f);
	glEnd();
	glEndList();
}

// ritorna in posizione iniziale
void Inizio(){
	xpos=0.0f;
	zpos=0.0f;
	yrot=0.0f;
	altezza=0.0f;
}

// funzione di inizializzazione generale di opengl
void InitGL(int Width, int Height){ 
	SetupTabella();
	LoadGLTextures();			// Load The Texture(s) 
	glEnable(GL_TEXTURE_2D);		// Enable Texture Mapping
	glClearColor(0.1f, 0.0f, 0.2f, 0.0f);	// This Will Clear The Background Color To Black
	glClearDepth(1.0);			// Enables Clearing Of The Depth Buffer
	glDepthFunc(GL_LESS);			// The Type Of Depth Test To Do
	glEnable(GL_DEPTH_TEST);		// Enables Depth Testing
	glShadeModel(GL_SMOOTH);		// Enables Smooth Color Shading
	glEnable(GL_COLOR_MATERIAL);
	glEnable(GL_CULL_FACE); 		// escludi le facce sul retro
	
	BuildList();				// display lists.

	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	gluPerspective(45.0f,(GLfloat)Width/(GLfloat)Height,0.1f,120.0f);
	glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
	//antialising...
	/*
	glHint(GL_LINE_SMOOTH_HINT, GL_NICEST);	
	glHint(GL_POLYGON_SMOOTH_HINT, GL_NICEST);
	glHint(GL_POINT_SMOOTH_HINT, GL_NICEST);	
	*/		
	// luci
	glLightfv(GL_LIGHT1, GL_POSITION, LightPos);		
  	glLightfv(GL_LIGHT1, GL_AMBIENT, LightAmb);		
  	glLightfv(GL_LIGHT1, GL_DIFFUSE, LightDif);		
  	glLightfv(GL_LIGHT1, GL_SPECULAR, LightSpc);
  	// materiali
  	glMaterialfv(GL_FRONT, GL_AMBIENT, MatAmb);
  	glMaterialfv(GL_FRONT, GL_DIFFUSE, MatDif);
  	glMaterialfv(GL_FRONT, GL_SPECULAR, MatSpc);
  	glMaterialfv(GL_FRONT, GL_SHININESS, MatShn);
	glColorMaterial(GL_FRONT,GL_AMBIENT_AND_DIFFUSE);
	glEnable(GL_LIGHT1);
	
	//glBlendFunc(GL_SRC_ALPHA,GL_ONE);
	glBlendFunc(GL_SRC_ALPHA, GL_ONE );
	//glBlendFunc(GL_ONE_MINUS_DST_COLOR,GL_ONE);
	Inizio();
	glMatrixMode(GL_MODELVIEW);
}



// funzione di resize della finestra
void ReSizeGLScene(int Width, int Height){
	if (Height==0)				// Prevent A Divide By Zero If The Window Is Too Small
		Height=1;

	glViewport(0, 0, Width, Height);	// Reset The Current Viewport And Perspective Transformation

	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	gluPerspective(45.0f,(GLfloat)Width/(GLfloat)Height,0.1f,120.0f);
	//glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
	
	glMatrixMode(GL_MODELVIEW);
}

// funzione di passaggio a vista ortogonale
void ViewOrtho(int x, int y){
	glMatrixMode(GL_PROJECTION);
	glPushMatrix();	
	glLoadIdentity();
	glOrtho(-x,x,-y,y,1,60);
	glMatrixMode(GL_MODELVIEW);
	glPushMatrix();
	glLoadIdentity();
}

// funzione di passaggio a vista prospettica
void ViewPerspective(void){
	glMatrixMode( GL_PROJECTION );
	glPopMatrix();
	glMatrixMode( GL_MODELVIEW );
	glPopMatrix();
}

// funzione facoltativa per disegnare un menu interno alla scena
void disegnaMenu(){

	int win_w,win_h,num;
	win_w=glutGet(GLUT_WINDOW_WIDTH)>>1;
	win_h=glutGet(GLUT_WINDOW_HEIGHT)>>1;
	glPushMatrix();	
	ViewOrtho(win_w,win_h);
	glDisable(GL_LIGHTING);
	
	
	glDisable(GL_DEPTH_TEST);
	
	//orizzontale con restanti
	glPushMatrix(); 
	glColor4f(1.0f,1.0f,1.0f,0.6f);
	glBindTexture(GL_TEXTURE_2D, rest);
	glBegin(GL_QUADS);
	glTexCoord2f(1.0f, 0.25f);glVertex3f((win_w)-62,  -(win_h)+20, -1.0f);
	glTexCoord2f(0.0f, 0.25f);glVertex3f((win_w)-200, -(win_h)+20, -1.0f);
	glTexCoord2f(0.0f, 0.0f);glVertex3f((win_w)-200,  -(win_h), -1.0f);
	glTexCoord2f(1.0f, 0.0f);glVertex3f((win_w)-62,  -(win_h), -1.0f);	
	glEnd();  
	glPopMatrix();
	
	//orizzontale con decine
	glPushMatrix(); 
	glColor4f(1.0f,1.0f,1.0f,0.6f);
	num=(restanti/10);
	if(num==0)
		glBindTexture(GL_TEXTURE_2D, zero);
	else	
		glBindTexture(GL_TEXTURE_2D, texture[num-1][filter]);
	glBegin(GL_QUADS);
	glTexCoord2f(1.0f, 1.0f);glVertex3f((win_w)-32,  -(win_h)+20, -1.0f);
	glTexCoord2f(0.0f, 1.0f);glVertex3f((win_w)-60, -(win_h)+20, -1.0f);
	glTexCoord2f(0.0f, 0.0f);glVertex3f((win_w)-60,  -(win_h), -1.0f);
	glTexCoord2f(1.0f, 0.0f);glVertex3f((win_w)-32,  -(win_h), -1.0f);	
	glEnd();  
	glPopMatrix();
	
	//orizzontale con unita'	
	glPushMatrix(); 
	glColor4f(1.0f,1.0f,1.0f,0.6f);
	num=(restanti%10);
	if(num==0)
		glBindTexture(GL_TEXTURE_2D, zero);
	else	
		glBindTexture(GL_TEXTURE_2D, texture[num-1][filter]);
	glBegin(GL_QUADS);
	glTexCoord2f(1.0f, 1.0f);glVertex3f((win_w)-2,  -(win_h)+20, -1.0f);
	glTexCoord2f(0.0f, 1.0f);glVertex3f((win_w)-30, -(win_h)+20, -1.0f);
	glTexCoord2f(0.0f, 0.0f);glVertex3f((win_w)-30,  -(win_h), -1.0f);
	glTexCoord2f(1.0f, 0.0f);glVertex3f((win_w)-2,  -(win_h), -1.0f);	
	glEnd();  
	glPopMatrix();
	
	glEnable(GL_BLEND);	
	
	//orizzontale senza niente	
	glPushMatrix(); 
	glColor4f(1.0f,1.0f,1.0f,0.1f);
	glBegin(GL_QUADS);
	glVertex3f((win_w)-200-2,  -(win_h)+20, -1.0f);
	glVertex3f(-(win_w), -(win_h)+20, -1.0f);
	glVertex3f(-(win_w),  -(win_h), -1.0f);
	glVertex3f((win_w)-200-2,  -(win_h), -1.0f);	
	glEnd();  
	glPopMatrix();
		
	//verticale trasparente alto
	glPushMatrix(); 
	glColor4f(1.0f,1.0f,1.0f,0.3f);
	glBegin(GL_QUADS);
	glVertex3f(  (win_w),  	   (win_h), -1.0f);
	glVertex3f(  (win_w)-200,  (win_h), -1.0f);
	glVertex3f(  (win_w)-200, -(win_h)+242+2, -1.01f);
	glVertex3f(  (win_w),     -(win_h)+242+2, -1.0f);	
	glEnd();  
	glPopMatrix();
	
	//verticale trasparente basso
	glPushMatrix(); 
	glColor4f(1.0f,1.0f,1.0f,0.3f);
	glBegin(GL_QUADS);
	glVertex3f(  (win_w),  	  -(win_h)+40, -1.0f);
	glVertex3f(  (win_w)-200, -(win_h)+40, -1.0f);
	glVertex3f(  (win_w)-200, -(win_h)+20+2, -1.01f);
	glVertex3f(  (win_w),     -(win_h)+20+2, -1.0f);	
	glEnd();  
	glPopMatrix();
	
	glDisable(GL_BLEND);	
	
	//verticale con menu
	glPushMatrix(); 
	glColor4f(1.0f,1.0f,1.0f,0.2f);
	glBindTexture(GL_TEXTURE_2D, menut);
	glBegin(GL_QUADS);
	glTexCoord2f(1.0f, 1.0f);glVertex3f(  (win_w),      -(win_h)+242, -1.0f);
	glTexCoord2f(0.0f, 1.0f);glVertex3f(  (win_w)-200,  -(win_h)+242, -1.0f);
	glTexCoord2f(0.0f, 0.0f);glVertex3f(  (win_w)-200,  -(win_h)+40+2, -1.01f);
	glTexCoord2f(1.0f, 0.0f);glVertex3f(  (win_w),      -(win_h)+40+2, -1.0f);	
	glEnd();  
	glPopMatrix();
	
	glEnable(GL_DEPTH_TEST);
		
	ViewPerspective();
		
	glPopMatrix();	
}

// funzione per disegnare uno sfondo
void disegnaSfondo(int s){	
	int win_w,win_h;
	win_w=glutGet(GLUT_WINDOW_WIDTH)>>1;
	win_h=glutGet(GLUT_WINDOW_HEIGHT)>>1;
	ViewOrtho(win_w,win_h);
	
	glDepthMask(GL_FALSE);			// disabilita scrittura nel depth buffer
	glDisable(GL_DEPTH_TEST);
	
	glBindTexture(GL_TEXTURE_2D, background);
	glBegin(GL_QUADS);
	glNormal3f(0.0f, 0.0f, 1.0f);
	glColor3f(1.0f,1.0f,1.0f);
	glTexCoord2f(1.0f, 1.0f);glVertex3f(win_w,  win_h, -1.0f);
	glTexCoord2f(0.0f, 1.0f);glVertex3f(-win_w, win_h, -1.0f);
	glTexCoord2f(0.0f, 0.0f);glVertex3f(-win_w, -win_h, -1.0f);
	glTexCoord2f(1.0f, 0.0f);glVertex3f(win_w,  -win_h, -1.0f);
	glEnd();  
		
	glEnable(GL_DEPTH_TEST);
	glDepthMask(GL_TRUE);
	ViewPerspective();	
}

// funzione per disegnare autore (always on top)
void disegnaAuthor(){

	int win_w,win_h;
	win_w=glutGet(GLUT_WINDOW_WIDTH)>>1;
	win_h=glutGet(GLUT_WINDOW_HEIGHT)>>1;
	glDisable(GL_LIGHTING);
	glPushMatrix();
	ViewOrtho(win_w,win_h);
		
	glDisable(GL_DEPTH_TEST);
			
	glColor3f(1.0f,1.0f,1.0f);
	glBindTexture(GL_TEXTURE_2D, foto);
	glBegin(GL_QUADS);		
	glTexCoord2f(0.0f, 0.0f);glVertex3f(-150.0f, -150.0f,  -1.0f);	// Bottom Left
	glTexCoord2f(1.0f, 0.0f);glVertex3f( 150.0f, -150.0f,  -1.0f);	// Bottom Right
	glTexCoord2f(1.0f, 1.0f);glVertex3f( 150.0f,  150.0f,  -1.0f);	// Top Right
	glTexCoord2f(0.0f, 1.0f);glVertex3f(-150.0f,  150.0f,  -1.0f);	// Top Left
    	glEnd();

	glEnable(GL_DEPTH_TEST);          
		
	ViewPerspective();
	glPopMatrix();
}

// funzione per disegnare minimap
void disegnaMinimap(){
	int off_w,off_h,y;
	int inx,iny;
	off_w=glutGet(GLUT_WINDOW_WIDTH)>>1;
	off_h=glutGet(GLUT_WINDOW_HEIGHT)>>1;
	
	glDisable(GL_LIGHTING);
	glPushMatrix();	
	
	ViewOrtho(off_w,off_h);
	
	off_w=-(off_w)+5;
	off_h=-(off_h)+5;
	inx=off_w+40+(xpos/2);
	iny=off_h+40-(zpos/2);  
		
	glDisable(GL_DEPTH_TEST);     
	glColor4f(0.0f,0.0f,0.9f,1.0f);
	glBegin(GL_QUADS);
	glVertex3f(  (off_w)+85,  (off_h)+85, -1.0f);
	glVertex3f(  (off_w)-5,  (off_h)+85, -1.0f);
	glVertex3f(  (off_w)-5,  (off_h)-5, -1.01f);
	glVertex3f(  (off_w)+85,  (off_h)-5, -1.0f);	
	glEnd();  
	
	glColor4f(1.0f,1.0f,1.0f,0.4f);
	glEnable(GL_BLEND);
    	for(y=0;y<=80;y=y+8){
    		glBegin(GL_LINES);
		glVertex3f(off_w+y, off_h, -1.0f);
		glVertex3f(off_w+y,  off_h+80, -1.0f);
		glEnd();  
		
		glBegin(GL_LINES);
		glVertex3f(off_w,  off_h+y, -1.0f);
		glVertex3f(off_w+80,  off_h+y, -1.0f);
		glEnd();  		
    	}
    	glDisable(GL_BLEND); 
    	if( (xpos>=-40)  && (xpos<=40) &&
    	    (zpos<=40)  && (zpos>=-40)){
    		glPushMatrix(); 
    		glTranslatef(off_w+40+xpos, off_h+40-zpos,-1.0f); 
    		glRotatef(yrot, 0, 0, 1.0f); 
    		inx=0;iny=0;
    		glBegin(GL_TRIANGLES);
    		glNormal3f(0.0f, 0.0f, 1.0f);
    		glColor3f(1.0f,1.0f,0.0f);
    		glVertex3f(0, iny+7, -1.0f);
    		glColor3f(0.0f,1.0f,0.0f);
    		glVertex3f(inx-5, iny-5, -1.0f);
    		glVertex3f(inx+5, iny-5, -1.0f);
    		glEnd();
    		glTranslatef(-off_w+40, -off_h+40,-1.0f);
    		glPopMatrix();
    	}
 
	glEnable(GL_DEPTH_TEST); 
		
	ViewPerspective();
	glPopMatrix();
}

// funzione per disegnare mirino
void disegnaMirino(){
	
	glPushMatrix();
	glDisable(GL_LIGHTING);
	ViewOrtho(glutGet(GLUT_WINDOW_WIDTH)>>1,glutGet(GLUT_WINDOW_HEIGHT)>>1);
	
	glDisable(GL_DEPTH_TEST);
	glColor3f(1.0f,1.0f,1.0f);
    	
    	glPushMatrix(); 	
	glBegin(GL_LINES);
	glVertex3f(-20.0f,  0.0f, -1.0f);
	glVertex3f(20.0f,  0.0f, -1.0f);
	glEnd();  
	glPopMatrix();
	
	glPushMatrix(); 
	glBegin(GL_LINES);
	glVertex3f(0.0f,  -20.0f, -1.0f);
	glVertex3f(0.0f,  20.0f, -1.0f);
	glEnd();  
	glPopMatrix();			
	
	glEnable(GL_DEPTH_TEST);
		
	ViewPerspective();
	glPopMatrix();	
}

// funzione di disegno principale
void DrawGLScene(){
	
	GLfloat xtrans, ztrans;
	GLfloat sceneroty;
	GLfloat i,j;
	int valore,inx,iny;
	
	xtrans = -xpos;
    	ztrans = -zpos;
    	sceneroty = 360.0f - yrot;
    
  	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	glLoadIdentity();	
	// movimenti di proiezione
    	glRotatef(sceneroty, 0, 1.0f, 0);
    	glTranslatef(xtrans, 0, ztrans);  
    	glTranslatef(0.0f,altezza, 0.0f); 
		
	glMatrixMode(GL_MODELVIEW);
	
	// disegna sfondo
	if(sfondo!=0){
	glPushAttrib( GL_ENABLE_BIT | GL_BLEND | GL_LIGHTING);
	glDisable(GL_LIGHTING);
	disegnaSfondo(sfondo);
	glPopAttrib();
	};
	
	/* INSERIMENTO CUBI */
	for(j=-12.0f;j<=12.0f;j=j+3.0f){
		for(i=-12.0f;i<=12.0f;i=i+3.0f){
			glPushMatrix();  
    			glTranslatef(i, j, -40.0f);
    			// faccia frontale
    			inx=calcolaIndice(i);
    			iny=calcolaIndice(j);
    			glColor3f(0.9f,0.8f,0.8f);
    			valore=temp[iny][inx];
    			// per vedere soluzione:
    			//valore=res[iny][inx];
    			//printf(" valore: %d  ---  iny: %d  ---  inx: %d \n",valore,iny,inx);
    			if(valore!=0){
    				glBindTexture(GL_TEXTURE_2D, texture[valore-1][filter]);
    				if(init[iny][inx]!=0){
    					glColor3f(1.0f,0.8f,0.0f);
    				}
    			}	
    			else{
    				glBindTexture(GL_TEXTURE_2D, 0);
    			}	
    			glBegin(GL_QUADS);
    			glNormal3f(0.0f, 0.0f, 1.0f);			
			glTexCoord2f(0.0f, 0.0f);glVertex3f(-1.0f, -1.0f,  1.0f);	// Bottom Left 
			glTexCoord2f(1.0f, 0.0f);glVertex3f( 1.0f, -1.0f,  1.0f);	// Bottom Right
			glTexCoord2f(1.0f, 1.0f);glVertex3f( 1.0f,  1.0f,  1.0f);	// Top Right 
			glTexCoord2f(0.0f, 1.0f);glVertex3f(-1.0f,  1.0f,  1.0f);	// Top Left 
    			glEnd();
    			
    			glCallList(cube);
 			glPopMatrix();
		}
	
	}	
	
	//printf("selezionato: %d %d \n",selx,sely);
	/* RIQUADRO */
	glPushMatrix(); 
	glTranslatef(curx,cury,PROFON_CORNICI);
	glCallList(quad);
	glPopMatrix();	 
	
	/* INSERIMENTO CORNICI */
	glColor3f(1.0f,0.8f,0.0f);
	glPushMatrix(); 
	glTranslatef(13.4f,0.0f,PROFON_CORNICI);
	glCallList(stripe);
	glPopMatrix();  
    		
    	glPushMatrix(); 
    	glTranslatef(-13.4f,0.0f,PROFON_CORNICI);
	glCallList(stripe);	
	glPopMatrix();
	
	glPushMatrix(); 
    	glRotatef(90.0f,0.0f,0.0f,1.0f);
	glTranslatef(-13.4f,0.0f,PROFON_CORNICI);
	glCallList(stripe);	
	glPopMatrix();	

	glPushMatrix(); 
    	glRotatef(90.0f,0.0f,0.0f,1.0f);
	glTranslatef(13.4f,0.0f,PROFON_CORNICI);
	glCallList(stripe);	
	glPopMatrix();	
	
	glPushMatrix(); 
    	glTranslatef(-4.5f,0.0f,PROFON_CORNICI);
	glCallList(stripe);	
	glPopMatrix();
	
	glPushMatrix(); 
	glTranslatef(4.5f,0.0f,PROFON_CORNICI);
	glCallList(stripe);	
	glPopMatrix();
	
	glPushMatrix(); 
    	glRotatef(90.0f,0.0f,0.0f,1.0f);
    	glTranslatef(-4.5f,0.0f,PROFON_CORNICI);
	glCallList(stripe);	
	glPopMatrix();
	
	glPushMatrix(); 
    	glRotatef(90.0f,0.0f,0.0f,1.0f);
    	glTranslatef(4.5f,0.0f,PROFON_CORNICI);
	glCallList(stripe);	
	glPopMatrix();  	
		
	/* MURO RETRO */
	/**/
	glPushMatrix(); 
	glColor3f(0.6f,0.1f,0.4f);
	glBegin(GL_QUADS);
	glNormal3f(0.0f, 0.0f, 1.0f);	
	glVertex3f(40.0f,  2.0f, -60.0f);
	glVertex3f(-40.0f, 2.0f, -60.0f);
	glVertex3f(-40.0f, -2.0f, -60.0f);
	glVertex3f(40.0f,  -2.0f, -60.0f);
	glEnd();  
	glPopMatrix();
	
	/* TERRENO */
	/**/
	glPushMatrix(); 
	glBegin(GL_QUADS);
	glNormal3f(0.0f, 1.0f, 0.0f);
	glColor3f(0.2f,0.8f,0.3f);
	glVertex3f(40.0f,  -14.0f, 40.0f);
	glVertex3f(40.0f,  -14.0f, -60.0f);
	glVertex3f(-40.0f,  -14.0f, -60.0f);
	glVertex3f(-40.0f,  -14.0f, 40.0f);
	glEnd();
	glPopMatrix();  	
	
	/* MURO BLU A SINISTRA */
	glPushMatrix(); 
	glBegin(GL_QUADS);
	glNormal3f(1.0f, 0.0f, 0.0f);
	glColor3f(0.0f,0.0f,0.4f);
	glVertex3f(-40.0f,  -2.0f, 40.0f);
	glVertex3f(-40.0f,  -2.0f, -60.0f);
	glVertex3f(-40.0f,  2.0f, -60.0f);
	glVertex3f(-40.0f,  2.0f, 40.0f);	
	glEnd();
	glPopMatrix();  	
	
	/* MURO ROSSO A DESTRA */	
	glPushMatrix(); 
	glBegin(GL_QUADS);
	glNormal3f(-1.0f, 0.0f, 0.0f);
	glColor3f(0.4f,0.0f,0.0f);
	glVertex3f(40.0f,  -2.0f, 40.0f);
	glVertex3f(40.0f,  2.0f, 40.0f);
	glVertex3f(40.0f,  2.0f, -60.0f);
	glVertex3f(40.0f,  -2.0f, -60.0f);
	glEnd();  
	glPopMatrix();	  	
	
	glPushAttrib( GL_ENABLE_BIT | GL_BLEND | GL_LIGHTING);
	glDisable(GL_LIGHTING);	
	
	if (mirino) {
		disegnaMirino();		
	}	
	if (blend) {
		disegnaMenu();		
	}
	if (minimap) {
		disegnaMinimap();		
	} 		
	if(author){
		disegnaAuthor();
	}
	
	glPopAttrib();	
	if (!light) {
	    glDisable(GL_LIGHTING);
	}
	else {
	    glEnable(GL_LIGHTING);
	}     
		
  	glutSwapBuffers();
}

// funzione tastiera
void keyPressed(unsigned char key, int x, int y){

	
    	usleep(100);

    	if (key == ESCAPE){ 
		glutDestroyWindow(window); 
		exit(1);                   
    	}
    	switch (key) {    
    		
		case ACAR:
		case aCAR:
			if(curx>-12)
				curx=(curx-3);
			else curx=12;
			break;
		case SCAR:
		case sCAR:
			if(curx<12)
				curx=(curx+3);
			else curx=-12;
			break;
		case WCAR:
		case wCAR:
			if(cury<12)
				cury=(cury+3);
			else cury=-12;
			break;
		case ZCAR:
		case zCAR:
			if(cury>-12)
				cury=(cury-3);
			else cury=12;
			break;
		case NUM0: resetCasella();break;
			case NUM1: scriviCasella(1);break;
		case NUM2: scriviCasella(2);break;
		case NUM3: scriviCasella(3);break;
		case NUM4: scriviCasella(4);break;
		case NUM5: scriviCasella(5);break;
		case NUM6: scriviCasella(6);break;
		case NUM7: scriviCasella(7);break;
		case NUM8: scriviCasella(8);break;
		case NUM9: scriviCasella(9);break;
	
		case bCAR: 
    		case BCAR: // ---B--- menu con blending.
			blend = blend ? 0 : 1;
		break;
		
		case vCAR: 
	    	case VCAR: // ---V--- cambia vista
	    		view = view ? 0 : 1; 
	    	if (!view) {
			ViewPerspective();	
		} else {
			ViewOrtho(  (glutGet(GLUT_WINDOW_WIDTH))*0.05f , (glutGet(GLUT_WINDOW_HEIGHT))*0.05f );			 
		}
		break;
		
		case fCAR:
	    	case FCAR: // ---F--- cambia filtro textures
			filter=(filter+1)%3;		
		break;
		
		case qCAR:
		case QCAR: // --- Q --- mostra credits
			author = author ? 0 : 1; 
		break;
		
		case mCAR:
		case MCAR: // --- M --- minimap.
			minimap = minimap ? 0 : 1; 
		break;
		
		case iCAR:
		case ICAR: // --- I --- mirino.
			mirino = mirino ? 0 : 1; 
		break;

		case rCAR:
		case RCAR: // --- R --- reset posizione
			Inizio(); 
		break;
		
		case cCAR:
		case CCAR: // --- C --- sfondo.
			sfondo = (sfondo +1)%2; 
		break;
		
		case lCAR: 
	   	case LCAR: // switch the lighting.
			light = light ? 0 : 1;
			if (!light) {
		    		glDisable(GL_LIGHTING);
			} else {
		    		glEnable(GL_LIGHTING);
			}
		break;

	    	default:
		break;
    	}
    	selx=calcolaIndice(curx);
    	sely=calcolaIndice(cury);
    	
	glutPostRedisplay();
}


//
void specialKeyPressed(int key, int x, int y) {
	usleep(100);
	if (!view) {
		switch (key) {    
		    	case GLUT_KEY_PAGE_UP:
				altezza -= 1.0f;
			break;
		    
		    	case GLUT_KEY_PAGE_DOWN:
				altezza += 1.0f;
			break;

		    	case GLUT_KEY_UP: 
				xpos -= (((float)sin(yrot*PISU180) * 0.05f)*SPEED);
				zpos -= (((float)cos(yrot*PISU180) * 0.05f)*SPEED);
				//printf("xpos: %4.3f  ---  zpos: %4.3f \n",xpos,zpos);	
				if (walkbiasangle >= 359.0f)
				    walkbiasangle = 0.0f;	
				else 
				    walkbiasangle+= 10;
			break;

		    	case GLUT_KEY_DOWN: 
				xpos += (((float)sin(yrot*PISU180) * 0.05f)*SPEED);
				zpos += (((float)cos(yrot*PISU180) * 0.05f)*SPEED);
				//printf("xpos: %4.3f  ---  zpos: %4.3f \n",xpos,zpos);	
				if (walkbiasangle <= 1.0f)
				    walkbiasangle = 359.0f;	
				else 
				    walkbiasangle-= 10;
			break;

		    	case GLUT_KEY_LEFT:
				yrot += RSPEED;
				//printf("yrot: %4.3f  \n",yrot);
			break;
		    
		   	case GLUT_KEY_RIGHT:
				yrot -= RSPEED;
				//printf("yrot: %4.3f  \n",yrot);
			break;

		    	default:
			break;	    
	    	}
	   	glutPostRedisplay();
	} 
}

void menu(int value){
    keyPressed((unsigned char)value, 0, 0);
}

int main(int argc, char **argv) { 
  	glutInit(&argc, argv);  
  	glutInitDisplayMode(GLUT_RGBA | GLUT_DOUBLE | GLUT_ALPHA | GLUT_DEPTH);  
  	glutInitWindowSize(700, 300); 
  	glutInitWindowPosition(0, 0);
  	window = glutCreateWindow("Fabio Rizzello --- OpenGL");  
  	glutDisplayFunc(&DrawGLScene);  
  	//glutFullScreen();
  	//glutIdleFunc(&DrawGLScene);
  	glutReshapeFunc(&ReSizeGLScene);
  	glutKeyboardFunc(&keyPressed);
	glutSpecialFunc(&specialKeyPressed);
	glutCreateMenu(menu);
	glutAddMenuEntry("texture filter", 'f');
	glutAddMenuEntry("lighting", 'l');
	glutAddMenuEntry("mirino", 'i');
	glutAddMenuEntry("credit", 'q');
	glutAddMenuEntry("minimap", 'm');
	glutAddMenuEntry("cambio vista", 'v');
	glutAddMenuEntry("background", 'c');
	glutAddMenuEntry("ritorno origine", 'r');
	glutAddMenuEntry("--------", 0);
	glutAddMenuEntry("esci", ESCAPE);
	glutAttachMenu(GLUT_RIGHT_BUTTON);
  	InitGL(740, 400);
  	glutMainLoop();  
  	return 1;
}
