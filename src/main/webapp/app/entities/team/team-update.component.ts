import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ITeam } from 'app/shared/model/team.model';
import { TeamService } from './team.service';

@Component({
    selector: 'jhi-team-update',
    templateUrl: './team-update.component.html'
})
export class TeamUpdateComponent implements OnInit {
    team: ITeam;
    isSaving: boolean;

    constructor(protected teamService: TeamService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ team }) => {
            this.team = team;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.team.id !== undefined) {
            this.subscribeToSaveResponse(this.teamService.update(this.team));
        } else {
            this.subscribeToSaveResponse(this.teamService.create(this.team));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ITeam>>) {
        result.subscribe((res: HttpResponse<ITeam>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
