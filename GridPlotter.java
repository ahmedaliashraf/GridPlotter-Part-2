package edu.kzoo;
// Class: GridPlotter
//
// Author: Your Name Here
//
// License Information:
//   This class is free software; you can redistribute it and/or modify
//   it under the terms of the GNU General Public License as published by
//   the Free Software Foundation.
//
//   This class is distributed in the hope that it will be useful,
//   but WITHOUT ANY WARRANTY; without even the implied warranty of
//   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//   GNU General Public License for more details.

import edu.kzoo.grid.ColorBlock;
import edu.kzoo.grid.Grid;
import edu.kzoo.grid.Location;
import edu.kzoo.grid.gui.GridChangeListener;
import edu.kzoo.grid.gui.nuggets.ColorChoiceMenu;

import java.awt.Color;

import javax.swing.JOptionPane;

/**
 *  Grid Plotter:<br>
 *
 *    The GridPlotter class provides methods for drawing in
 *    a grid by placing color blocks in its cells.
 *    Each drawing method should take zero arguments, should
 *    have a void return type, and should have a name that conforms
 *    to the on...ButtonClick format.  (This restriction allows the
 *    GridPlotterGUI to recognize drawing methods, for which
 *    it automatically generates buttons.)
 *
 *  @author Ahmed Ashraf (based on a template provided by Alyce Brady)
 *  @version 10-27-2014
 **/

public class GridPlotter implements GridChangeListener
{
  // Instance Variables: Encapsulated data for EACH GridPlotter object
    private Grid theGrid = null;
    private GridPlotterGUI display = null;
    private ColorChoiceMenu drawingColorChooser = null;

  // constructors and initialization

    /** Constructs an object that could draw in a grid.
     *      @param disp    an object that knows how to display a grid
     *      @param colorChooser  a menu for choosing the color block color
     **/
    public GridPlotter(GridPlotterGUI disp,
                       ColorChoiceMenu colorChooser)
    {
        this.display = disp;
        this.drawingColorChooser = colorChooser;
    }

    /** Sets the grid in which to draw.
     **/
    public void reactToNewGrid(Grid grid)
    {
        theGrid = grid;
    }

  // drawing methods

    /** Removes all objects from the grid.
     **/
    public void onClearGridButtonClick()
    {
        //     Should call ensureEmpty for every location in the grid.
    	// Clears the colored/filled grid. DOES NOT REMOVE THE GRID ITSELF.
    	for ( int row = 0; row < theGrid.numRows(); row++ )
        {
            for ( int column = 0; column < theGrid.numCols(); column++ )
            {
            	ensureEmpty(row, column);
            }
        }
        // Display the grid after it has been completely cleared.
        display.showGrid();
    }

    /** Fills in all the cells of the grid using a row-major traversal.
     **/
    public void onRowMajorFillButtonClick()
    {
        for ( int row = 0; row < theGrid.numRows(); row++ )
        {
            for ( int column = 0; column < theGrid.numCols(); column++ )
            {
                placeColorBlock(row, column);
            }
        }
    }

    /** Fills in all the cells of the grid using a column-major traversal.
     **/
    public void onColMajorFillButtonClick()
    {
    	for ( int column = 0; column < theGrid.numCols(); column++ )
        {
            for ( int row = 0; row < theGrid.numRows(); row++ )
            {
                placeColorBlock(row, column);
            }
        }
    }

    /** Fills in all the cells of the grid using a reverse row-major traversal.
     **/
    public void onReverseRowMajorFillButtonClick()
    {
    	for ( int row = theGrid.numRows()-1; row >= 0; row-- )
        {
            for ( int column = 0; column < theGrid.numCols(); column++ )
            {
                placeColorBlock(row, column);
            }
        }
    }
    /** Fills in all the cells of the grid using a reverse column-major traversal.
     **/
    public void onReverseColMajorFillButtonClick()
    {
    	for ( int column = theGrid.numCols()-1; column >= 0; column-- )
        {
    		for ( int row = theGrid.numRows()-1; row >= 0; row-- )
            {
                placeColorBlock(row, column);
            }
        }
    }
    /** Creates a diagonal from upper-left of the grid the lower-right.
     **/
    public void onDiagonalButtonClick()
    {
    	int numRows = theGrid.numRows();
    	if (theGrid.numRows() > theGrid.numCols())
   		{
    		numRows = theGrid.numCols();
   		}
    	for ( int row = 0; row < numRows; row++ )
        {    		
    		int column = row;
    		placeColorBlock(row, column);
        }
    	
    }
    /** Creates a diagonal from upper-right of the grid the lower-left.
     **/
    public void onReverseDiagonalButtonClick()
    {
    	int row = 0; 
    	for ( int col = theGrid.numCols()-1; col >= 0; col-- )
    	{
    		placeColorBlock(row, col);
        	row++;
        	if(row > theGrid.numRows()-1)
    		{
    			break;
    		}
        }	
    }
    /** Creates X on the Grid using Diagonal and Reverse Diagonal.
     **/
    public void onXmarksSpotButtonClick()
    {
    	int x= 2, y= 4;
    	if(x==2)
    	{
    		int numRows = theGrid.numRows();
        	if (theGrid.numRows() > theGrid.numCols())
       		{
        		numRows = theGrid.numCols();
       		}
        	for ( int row = 0; row < numRows; row++ )
            {    		
        		int column = row;
        		placeColorBlock(row, column);
            }
    	} 
    	if(y==4)
    	{
    		int row = 0; 
        	for ( int col = theGrid.numCols()-1; col >= 0; col-- )
        	{
        		placeColorBlock(row, col);
            	row++;
            	if(row > theGrid.numRows()-1)
        		{
        			break;
        		}
            }	
    	}
    }
    /** Creates a triangle on the Grid by filling cells from upper left to lower right.
     **/
    public void onTriangleButtonClick()
    {
    	if (theGrid.numRows() <= theGrid.numCols())
    	{
    		for ( int row = 0; row < theGrid.numRows(); row++ )
            {
                for ( int column = 0; column <= row; column++ )
                {
                    placeColorBlock(row, column);
                }
            }
    	} else 
    	{
    		int numRows = theGrid.numRows();
        	if (theGrid.numRows() > theGrid.numCols())
       		{
        		numRows = theGrid.numCols();
       		}
        	for ( int row = 0; row < numRows; row++ )
            {
                for ( int column = 0; column <= row; column++ )
                {
                    placeColorBlock(row, column);
                }
            }
        	for ( int row = theGrid.numRows()-(theGrid.numRows()-theGrid.numCols()); row < theGrid.numRows(); row++ )
            {
                for ( int column = 0; column < theGrid.numCols(); column++ )
                {
                    placeColorBlock(row, column);
                }
            }
    	}
    	
    }
    /** Draws a border around the grid's perimeter.
     **/
    public void onPerimeterButtonClick()
    {
    	int row1 = 0, row2 = theGrid.numRows()-1, col1 = 0, col2 = theGrid.numCols()-1;
    	for ( int col = 0; col < theGrid.numCols(); col++ )
    	{
    		placeColorBlock(row1, col);
    	}
    	for ( int row = 0; row < theGrid.numRows(); row++ )
        {
    		placeColorBlock(row, col2);
        }
    	for ( int col = theGrid.numCols()-1; col >= 0; col-- )
    	{
    		placeColorBlock(row2, col);
    	}
    	for ( int row = theGrid.numRows()-1; row >= 0; row-- )
        {
    		placeColorBlock(row, col1);
        }
    }
    /** Draws a house consisting of walls, roof and windows. 
     * @param onWallButtonClick() creates walls.
     * @param onRoofButtonClick() draws the roof.
     * @param onWindowsButtonClick() creates four windows.
     * @param The grid must have at least 20 rows and 20 column for the pictured to be drawn correctly. 
     **/
    public void onWallButtonClick()
    {
    	int row1 = (theGrid.numRows()/2)-2, row2 = theGrid.numRows()-1, col1 = 0, col2 = theGrid.numCols()-1;
    	for ( int col = 0; col < theGrid.numCols(); col++ )
    	{
    		placeColorBlock(row1, col);
    	}
    	for ( int row = row1; row < theGrid.numRows(); row++ )
        {
    		placeColorBlock(row, col2);
        }
    	for ( int col = theGrid.numCols()-1; col >= 0; col-- )
    	{
    		placeColorBlock(row2, col);
    	}
    	for ( int row = theGrid.numRows()-1; row >= row1; row-- )
        {
    		placeColorBlock(row, col1);
        }
    }
  
    public void onRoofButtonClick()
    {
    	int row1 = (theGrid.numRows()/2)-3;
    	int colDy = 0, colDyL = theGrid.numCols();
    	for (int x = 0; x >= 0; x++)
    	{
    		for ( int col = colDy; col < colDyL; col++ )
    		{    		
    			placeColorBlock(row1, col);
    		}
    		row1--; colDy++; colDyL--;
    		if (row1<0)
    		{
    			break;
    		}
    	}
    }
 
    public void onWindowsButtonClick()
    {
    	int row1 = theGrid.numRows()-8;
    	int col1 = theGrid.numCols()/7;
    	placeColorBlock(row1, col1);
    	placeColorBlock(row1, col1+1);
    	placeColorBlock(row1, col1+4);
    	placeColorBlock(row1, col1+5);
    	placeColorBlock(row1, col1+8);
    	placeColorBlock(row1, col1+9);
    	placeColorBlock(row1, col1+12);
    	placeColorBlock(row1, col1+13);
    	placeColorBlock(row1+1, col1);
    	placeColorBlock(row1+1, col1+1);
    	placeColorBlock(row1+1, col1+4);
    	placeColorBlock(row1+1, col1+5);
    	placeColorBlock(row1+1, col1+8);
    	placeColorBlock(row1+1, col1+9);
    	placeColorBlock(row1+1, col1+12);
    	placeColorBlock(row1+1, col1+13);
    	placeColorBlock(row1+2, col1);
    	placeColorBlock(row1+2, col1+1);
    	placeColorBlock(row1+2, col1+4);
    	placeColorBlock(row1+2, col1+5);
    	placeColorBlock(row1+2, col1+8);
    	placeColorBlock(row1+2, col1+9);
    	placeColorBlock(row1+2, col1+12);
    	placeColorBlock(row1+2, col1+13);
    	
    }

  // helper methods that are called by other methods

    /** Ensures that the specified location is empty by removing the object
     *  there, if there is one.
     *      @param row      row number of location that should be empty
     *      @param column   column number of location that should be empty
     **/
    private void ensureEmpty(int row, int column)
    {
        // If the specified location in the grid is not empty,
        // remove whatever object is there.
        Location loc = new Location(row, column);
        if ( ! theGrid.isEmpty(loc) )
            theGrid.remove(theGrid.objectAt(loc));
        
    }

    /** Puts a color block in the specified location and redisplays the grid.
     *      @param row      row in which to place the new color block
     *      @param column   column in which to place the new color block
     **/
    private void placeColorBlock(int row, int column)
    {
        // First remove any color block that happens to be at this location.
        ensureEmpty(row, column);

        // Determine the color to use for this color block.
        Color color = drawingColorChooser.currentColor();

        // Construct the color block and add it to the grid at the
        // specificed location.  Then display the grid.
        Location loc = new Location(row, column);
        theGrid.add(new ColorBlock(color), loc);
        display.showLocation(loc);
    }

}
