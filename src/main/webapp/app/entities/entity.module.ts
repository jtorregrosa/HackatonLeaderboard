import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { HackatonLeaderboardStageModule } from './stage/stage.module';
import { HackatonLeaderboardTeamModule } from './team/team.module';
import { HackatonLeaderboardScoreModule } from './score/score.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        HackatonLeaderboardStageModule,
        HackatonLeaderboardTeamModule,
        HackatonLeaderboardScoreModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HackatonLeaderboardEntityModule {}
