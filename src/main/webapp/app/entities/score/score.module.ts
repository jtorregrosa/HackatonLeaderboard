import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { HackatonLeaderboardSharedModule } from 'app/shared';
import {
    ScoreComponent,
    ScoreDetailComponent,
    ScoreUpdateComponent,
    ScoreDeletePopupComponent,
    ScoreDeleteDialogComponent,
    scoreRoute,
    scorePopupRoute
} from './';

const ENTITY_STATES = [...scoreRoute, ...scorePopupRoute];

@NgModule({
    imports: [HackatonLeaderboardSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [ScoreComponent, ScoreDetailComponent, ScoreUpdateComponent, ScoreDeleteDialogComponent, ScoreDeletePopupComponent],
    entryComponents: [ScoreComponent, ScoreUpdateComponent, ScoreDeleteDialogComponent, ScoreDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HackatonLeaderboardScoreModule {}
