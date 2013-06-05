package org.firepick.firebom;
/*
    Copyright (C) 2013 Karl Lew <karl@firepick.org>. All rights reserved.
    DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
    
    This file is part of FirePick Software.
    
    FirePick Software is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    FirePick Software is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with FirePick Software.  If not, see <http://www.gnu.org/licenses/>.
    
    For more information about FirePick Software visit http://firepick.org
 */


import org.junit.Test;

import java.io.IOException;
import java.net.URL;

import static org.junit.Assert.assertEquals;

public class PartFactoryTest {
    @Test
    public void testShapeways() throws Exception {
        testPart("http://shpws.me/nekC", "DL55", 4.28, 4.28, 1);
        testPart("http://www.shapeways.com/model/898050/dl55.html?li=productBox-search", "DL55", 4.28, 4.28, 1);
        testPart("http://www.shapeways.com/model/898050/dl55.html", "DL55", 4.28, 4.28, 1);
    }

    @Test
    public void testMcMasterCarr() throws Exception {
        testPart("http://www.mcmaster.com/#91290A115", "91290A115", 0.0639, 6.39, 100);
        testPart("http://www.mcmaster.com/#57485K63", "57485K63", 1.55, 1.55, 1);
        testPart("http://www.mcmaster.com/#95601A295", "95601A295", 0.0227, 2.27, 100);
    }

    @Test
    public void testGitHub() throws Exception {
        new PartTester("https://github.com/firepick1/FirePick/wiki/D7IH")
                .testId("D7IH").testUnitCost(9.77).testPackageCost(9.77).testPackageUnits(1);
        new PartTester("https://github.com/firepick1/FirePick/wiki/F3WF")
                .testId("F3WF").testUnitCost(0.0227).testPackageCost(0.0227).testPackageUnits(1);
    }

    private Part testPart(String url, String id, double unitCost, double packageCost, double packageUnits) throws IOException {
        Part part = new PartFactory().createPart(new URL(url));
        assertEquals(unitCost, part.getUnitCost(), 0);
        assertEquals(packageCost, part.getPackageCost(), 0);
        assertEquals(id, part.getId());
        assertEquals(packageUnits, part.getPackageUnits(), 0);
        return part;
    }
}
