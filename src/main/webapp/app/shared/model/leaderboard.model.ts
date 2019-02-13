import { ILeaderboardEntry } from 'app/shared/model//leaderboardentry.model';

export interface ILeaderboard {
    entries?: ILeaderboardEntry[];
}

export class Leaderboard implements ILeaderboard {
    constructor(public entries?: ILeaderboardEntry[]) {}
}
