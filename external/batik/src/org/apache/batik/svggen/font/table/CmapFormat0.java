/*

   Copyright 2001  The Apache Software Foundation 

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

 */
package org.apache.batik.svggen.font.table;

import java.io.IOException;
import org.apache.batik.svggen.font.*;

/**
 * Simple Macintosh cmap table, mapping only the ASCII character set to glyphs.
 *
 * @version $Id: CmapFormat0.java,v 1.4 2004/09/01 09:35:23 deweese Exp $
 * @author <a href="mailto:david@steadystate.co.uk">David Schweinsberg</a>
 */
public class CmapFormat0 extends CmapFormat {

    private int[] glyphIdArray = new int[256];
    private int first, last;

    protected CmapFormat0(RandomAccessFileEmulator raf) throws IOException {
        super(raf);
        format = 0;
        first = -1;
        for (int i = 0; i < 256; i++) {
            glyphIdArray[i] = raf.readUnsignedByte();
            if (glyphIdArray[i] > 0) {
                if (first == -1) first = i;
                last = i;
            }
        }
    }

    public int getFirst() { return first; }
    public int getLast()  { return last; }

    public int mapCharCode(int charCode) {
        if (0 <= charCode && charCode < 256) {
            return glyphIdArray[charCode];
        } else {
            return 0;
        }
    }
}
