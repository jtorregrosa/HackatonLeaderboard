import { IScore } from 'app/shared/model//score.model';

export interface ITeam {
    id?: number;
    name?: string;
    description?: string;
    scores?: IScore[];
}

export class Team implements ITeam {
    constructor(public id?: number, public name?: string, public description?: string, public scores?: IScore[]) {}
}
