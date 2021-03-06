/*
 * Copyright 2018 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.drools.workbench.screens.scenariosimulation.client.producers;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.drools.workbench.screens.scenariosimulation.client.models.ScenarioGridModel;
import org.drools.workbench.screens.scenariosimulation.client.renderers.ScenarioGridRenderer;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGrid;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGridLayer;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGridPanel;

/**
 * <code>@Dependent</code> <i>Producer</i> for a given {@link ScenarioGridPanel}
 */
@Dependent
public class ScenarioGridPanelProducer {

    @Inject
    protected ScenarioGridLayer scenarioGridLayer;

    @Inject
    protected ScenarioGridPanel scenarioGridPanel;

    @PostConstruct
    public void init() {
        final ScenarioGrid scenarioGrid = new ScenarioGrid(new ScenarioGridModel(false),
                                                           scenarioGridLayer,
                                                           new ScenarioGridRenderer(false),
                                                           scenarioGridPanel);
        scenarioGridLayer.addScenarioGrid(scenarioGrid);
        scenarioGridPanel.add(scenarioGridLayer);

    }

    public ScenarioGridPanel getScenarioGridPanel() {
        return scenarioGridPanel;
    }

}
