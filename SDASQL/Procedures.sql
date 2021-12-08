Drop Database States
create database States
use States
GO
drop table 
create table STATE
(
	StateName varchar(1000) primary key,
	String nvarchar(1000),
);
GO
drop table STATE

select * FROM STATE

CREATE Procedure insertState

@StateName varchar(1000),
@String varchar(100),
@DTOInsertion DateTime

AS
BEGIN
	
	declare @state varchar
	select @state=StateName from STATE where StateName=@StateName;
	if @state is  null               --if the state user is saving is a new state
	BEGIN
		INSERT INTO STATE(StateName,String,DTOInsertion)
		VALUES(@StateName,@String,GETDATE())
	END
END

Go
drop procedure LoadState
CREATE Procedure LoadState

@StateName varchar(1000),
@output nvarchar(1000) out

AS
BEGIN
	declare @ID                             
	select @output=String from STATE where StateName=@StateName;
	return @output
END

CREATE Procedure DeleteState
@StateName varchar(1000)

AS
BEGIN
	declare @ID                             
	delete from STATE where StateName=@StateName;
END

