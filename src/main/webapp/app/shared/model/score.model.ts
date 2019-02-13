import { IStage } from 'app/shared/model//stage.model';
import { ITeam } from 'app/shared/model//team.model';

export interface IScore {
    id?: number;
    value?: number;
    stage?: IStage;
    team?: ITeam;
}

export class Score implements IScore {
    constructor(public id?: number, public value?: number, public stage?: IStage, public team?: ITeam) {}
}
