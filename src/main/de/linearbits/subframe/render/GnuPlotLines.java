/*
 * SUBFRAME - Simple Java Benchmarking Framework
 * Copyright (C) 2012 - 2013 Fabian Prasser
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package de.linearbits.subframe.render;

import java.util.List;

import de.linearbits.subframe.graph.PlotLines;
import de.linearbits.subframe.graph.Point2D;
import de.linearbits.subframe.graph.Point3D;

/**
 * GnuPlot implementation of a lines plot
 * 
 * @author Fabian Prasser
 */
class GnuPlotLines extends GnuPlot<PlotLines> {

    /**
     * Creates a new plot
     * 
     * @param plot
     * @param params
     */
    protected GnuPlotLines(PlotLines plot, GnuPlotParams params) {
        super(plot, params);
    }

    @Override
    protected String getData() {

        StringBuffer buffer = new StringBuffer();
        if (plot.isErrorBars()) {
            for (Point3D point : plot.getSeries3D().getData()) {
                buffer.append(point.x);
                buffer.append(" ");
                buffer.append(point.y);
                buffer.append(" ");
                buffer.append(point.z);
                buffer.append(" ");
                buffer.append("\n");
            }
        } else {
            for (Point2D point : plot.getSeries2D().getData()) {
                buffer.append(point.x);
                buffer.append(" ");
                buffer.append(point.y);
                buffer.append("\n");
            }
        }
        return buffer.toString();
    }

    @Override
    protected String getSource(String filename) {

        List<String> gpCommands = getGenericCommands(filename, plot);

        if (plot.isErrorBars()) {
            gpCommands.add("plot '" + filename + ".dat' using 1:2 with " + params.lineStyle.val + " lw 1.5 t \"\", \\");
            gpCommands.add("'' using 1:2:3 with errorbars t \"\"");
        } else {
            gpCommands.add("plot '" + filename + ".dat' using 1:2 with " + params.lineStyle.val + " lw 3.5 ps 1.5 t \"\"");
        }

        StringBuffer buffer = new StringBuffer();
        for (String line : gpCommands) {
            buffer.append(line).append("\n");
        }
        return buffer.toString();
    }
}
