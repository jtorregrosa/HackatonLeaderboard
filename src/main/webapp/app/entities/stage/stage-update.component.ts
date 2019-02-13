import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IStage } from 'app/shared/model/stage.model';
import { StageService } from './stage.service';

@Component({
    selector: 'jhi-stage-update',
    templateUrl: './stage-update.component.html'
})
export class StageUpdateComponent implements OnInit {
    stage: IStage;
    isSaving: boolean;

    constructor(protected stageService: StageService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ stage }) => {
            this.stage = stage;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.stage.id !== undefined) {
            this.subscribeToSaveResponse(this.stageService.update(this.stage));
        } else {
            this.subscribeToSaveResponse(this.stageService.create(this.stage));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IStage>>) {
        result.subscribe((res: HttpResponse<IStage>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
