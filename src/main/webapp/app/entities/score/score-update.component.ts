import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IScore } from 'app/shared/model/score.model';
import { ScoreService } from './score.service';
import { IStage } from 'app/shared/model/stage.model';
import { StageService } from 'app/entities/stage';
import { ITeam } from 'app/shared/model/team.model';
import { TeamService } from 'app/entities/team';

@Component({
    selector: 'jhi-score-update',
    templateUrl: './score-update.component.html'
})
export class ScoreUpdateComponent implements OnInit {
    score: IScore;
    isSaving: boolean;

    stages: IStage[];

    teams: ITeam[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected scoreService: ScoreService,
        protected stageService: StageService,
        protected teamService: TeamService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ score }) => {
            this.score = score;
        });
        this.stageService.query().subscribe(
            (res: HttpResponse<IStage[]>) => {
                this.stages = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.teamService.query().subscribe(
            (res: HttpResponse<ITeam[]>) => {
                this.teams = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.score.id !== undefined) {
            this.subscribeToSaveResponse(this.scoreService.update(this.score));
        } else {
            this.subscribeToSaveResponse(this.scoreService.create(this.score));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IScore>>) {
        result.subscribe((res: HttpResponse<IScore>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackStageById(index: number, item: IStage) {
        return item.id;
    }

    trackTeamById(index: number, item: ITeam) {
        return item.id;
    }
}
