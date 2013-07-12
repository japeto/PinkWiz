import java.awt.*;
import java.lang.*;
import java.io.*;
import java.util.*;

import javax.swing.*;
/*
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 *
 *
 * @author Jefferson Pe\~na Torres
 *
 */


public class DrawHistogram extends JPanel {

        private int[] values;
        private String title;
        private Color colorChart;

        public DrawHistogram(int[] v,String t,Color c) {
                values = v;
                title = "Histogram:"+t;
                colorChart=c;
//                Logger().status("Show Histogram by "+t);
        }
        public void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (values == null || values.length == 0)
                        return;

                double minValue = 0;
                double maxValue = 0;
                for (int i = 0; i < values.length; i++) { // mayor y menor valor
                        if (minValue > values[i])
                        minValue = values[i];
                        if (maxValue < values[i])
                        maxValue = values[i];
                }
                Dimension d = getSize();
                int clientWidth = 600;//d.width;
                int clientHeight = 100;//d.height;
                int barWidth = clientWidth / values.length;

                Font titleFont = new Font("SansSerif", Font.BOLD, 20);
                FontMetrics titleFontMetrics = g.getFontMetrics(titleFont);
                Font labelFont = new Font("SansSerif", Font.PLAIN, 10);
                FontMetrics labelFontMetrics = g.getFontMetrics(labelFont);

                int titleWidth = titleFontMetrics.stringWidth(title);
                int y = titleFontMetrics.getAscent();
                int x = (clientWidth - titleWidth) / 2;
                g.setFont(titleFont);
                g.drawString(title, x, y);

                int top = titleFontMetrics.getHeight();
                int bottom = labelFontMetrics.getHeight();
                        if (maxValue == minValue)
                                return;
                double scale = (clientHeight - top - bottom) / (maxValue - minValue);
                y = clientHeight - labelFontMetrics.getDescent();
                g.setFont(labelFont);

                for (int i = 0; i < values.length; i++) {
                        int valueX = i * barWidth + 5;
                        int valueY = top;
                        int height = (int) (values[i] * scale);
                        if (values[i] >= 0)
                                valueY += (int) ((maxValue - values[i]) * scale);
                        else {
                                valueY += (int) (maxValue * scale);
                                height = -height;
                        }
                        g.setColor(colorChart);
                        g.fillRect(valueX, valueY, barWidth - 2, height);
                        g.drawRect(valueX, valueY, barWidth - 2, height);
                }
        }

}
